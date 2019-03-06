package ssm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import ssm.bean.School;
import ssm.bean.Skin;
import ssm.mapper.DemoMapper;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class RedisService {
    private static final Logger log = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DemoMapper demoMapper;

    private static final long LOCK_TIMEOUT = 5 * 1000;

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final String LOCK_NO = "redis_distribution_lock_no_";
    private static int i = 0;

    public School playJedis() {
        log.info("进入操作redis");
        School school = null;
        String json = redisTemplate.opsForValue().get("mine_lwy");
        try {
            if (StringUtils.isNotBlank(json)) {
                school = JSONObject.parseObject(json, School.class);
            }
        } catch (Exception e) {
            log.error("redis获取数据失败");
        }
        return school;
    }

    public void insert(Object object, Long id) {
        log.info("进入操作redis");
        redisTemplate.opsForValue().set("zero:" + id, JSON.toJSONString(object));
    }

    public void insertSkin(Object object, Long id) {
        log.info("进入操作redis 存储英雄皮肤");
        redisTemplate.opsForValue().set("zeroSkin:" + id, JSON.toJSONString(object));
    }

    public String getJsonObject(String key) {
        log.info("进入操作redis");
        String json = "";
        try {
            json = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("redis获取数据失败");
        }
        return json;
    }

    public void insert(Object object, String key) {
        log.info("进入操作redis");
        redisTemplate.opsForValue().set(key, JSON.toJSONString(object));
    }

    /*
     * redis加锁(有问题)
     * */
    public synchronized boolean lock(String lockKey) {
        boolean locked = false;
        /*该方法会在没有key时，设置key;存在key时返回false；因此可以通过该方法及设置key的有效期，判断是否有其它线程持有锁*/
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, "lockvalue");
        if (success != null && success) {
            redisTemplate.expire(lockKey, 3, TimeUnit.SECONDS);
            locked = true;
        } else {
            locked = false;
        }
        return locked;
    }

    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    /**
     * 加锁
     * 取到锁加锁，取不到锁一直等待知道获得锁
     *
     * @param lockKey
     * @param threadName
     * @return
     */
    public synchronized long lock(String lockKey, String threadName) {
        log.info(threadName + "开始执行加锁");
        while (true) { //循环获取锁
            //锁时间
            Long lock_timeout = currtTimeForRedis() + LOCK_TIMEOUT + 1;
            if (redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    //定义序列化方式
                    RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                    byte[] value = serializer.serialize(lock_timeout.toString());
                    boolean flag = redisConnection.setNX(lockKey.getBytes(), value);
                    return flag;
                }
            })) {
                //如果加锁成功
                log.info(threadName + "加锁成功 ++++ 111111");
                //设置超时时间，释放内存
                redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);
                return lock_timeout;
            } else {
                //获取redis里面的时间
                String result = redisTemplate.opsForValue().get(lockKey);
                Long currt_lock_timeout_str = result == null ? null : Long.parseLong(result);
                //锁已经失效
                if (currt_lock_timeout_str != null && currt_lock_timeout_str < System.currentTimeMillis()) {
                    //判断是否为空，不为空时，说明已经失效，如果被其他线程设置了值，则第二个条件判断无法执行
                    //获取上一个锁到期时间，并设置现在的锁到期时间
                    Long old_lock_timeout_Str = Long.valueOf(redisTemplate.opsForValue().getAndSet(lockKey, lock_timeout.toString()));
                    if (old_lock_timeout_Str != null && old_lock_timeout_Str.equals(currt_lock_timeout_str)) {
                        //多线程运行时，多个线程签好都到了这里，但只有一个线程的设置值和当前值相同，它才有权利获取锁
                        log.info(threadName + "加锁成功 ++++ 22222");
                        //设置超时间，释放内存
                        redisTemplate.expire(lockKey, LOCK_TIMEOUT, TimeUnit.MILLISECONDS);

                        //返回加锁时间
                        return lock_timeout;
                    }
                }
            }

            try {
                log.info(threadName + "等待加锁， 睡眠100毫秒");
//        TimeUnit.MILLISECONDS.sleep(100);
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解锁
     *
     * @param lockKey
     * @param lockValue
     * @param threadName
     */
    public synchronized void unlock(String lockKey, long lockValue, String threadName) {
        log.info(threadName + "执行解锁==========");//正常直接删除 如果异常关闭判断加锁会判断过期时间
        //获取redis中设置的时间
        String result = redisTemplate.opsForValue().get(lockKey);
        Long currt_lock_timeout_str = result == null ? null : Long.valueOf(result);

        //如果是加锁者，则删除锁， 如果不是，则等待自动过期，重新竞争加锁
        if (currt_lock_timeout_str != null && currt_lock_timeout_str == lockValue) {
            redisTemplate.delete(lockKey);
            log.info(threadName + "解锁成功------------------");
        }
    }

    /**
     * 多服务器集群，使用下面的方法，代替System.currentTimeMillis()，获取redis时间，避免多服务的时间不一致问题！！！
     *
     * @return
     */
    public long currtTimeForRedis() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.time();
            }
        });
    }

    public void task(String name) {
        //    System.out.println(name + "任务执行中"+(i++));

        //加锁时间
        Long lockTime;
        if ((lockTime = lock((LOCK_NO + 1) + "", name)) != null) {
            //开始执行任务
            System.out.println(name + "任务执行中" + (i++));
            //任务执行完毕 关闭锁
            unlock((LOCK_NO + 1) + "", lockTime, name);
        }

    }

    /**
    * 测试缓存穿透问题
    * */
    public void testRedisBoom() throws Exception{
        // String json = redisTemplate.opsForValue().get("mine_lwy");
        // 缓存为空时查询数据库
        // if (!StringUtils.isNotBlank(json)) {
            synchronized(this) {
                String json = redisTemplate.opsForValue().get("mine_lwy");
                if (!StringUtils.isNotBlank(json)) {
                    json= JSON.toJSONString(demoMapper.findZeroSkin());
                    // 放回缓存(设置缓存10秒后自动过期)
                    redisTemplate.opsForValue().set("mine_lwy", json, 10, TimeUnit.SECONDS);
                    System.out.println("数据库中取数据");
                } else {
                    System.out.println("从缓存中取数据");
                }
            }
/*        } else {
            System.out.println("从缓存中取数据");
        }*/
    }
}
