package ssm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ssm.bean.School;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private static final Logger log = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private StringRedisTemplate redisTemplate;

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

    public void insert (Object object, Long id) {
        log.info("进入操作redis");
        redisTemplate.opsForValue().set("zero:"+id,JSON.toJSONString(object));
    }
    public void insertSkin (Object object, Long id) {
        log.info("进入操作redis 存储英雄皮肤");
        redisTemplate.opsForValue().set("zeroSkin:"+id,JSON.toJSONString(object));
    }

    public String getJsonObject(String key) {
        log.info("进入操作redis");
        School school = null;
        String json = "";
        try {
            json = redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            log.error("redis获取数据失败");
        }
        return json;
    }

    public void insert (Object object, String key) {
        log.info("进入操作redis");
        redisTemplate.opsForValue().set(key,JSON.toJSONString(object));
    }
}
