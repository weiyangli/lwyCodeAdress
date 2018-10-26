package ssm.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import ssm.bean.School;

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
}
