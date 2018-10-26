package ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.bean.School;
import ssm.bean.Student;
import ssm.mapper.DemoMapper;

import java.util.List;

@Service
public class DemoService {
    @Autowired
    private DemoMapper demoMapper;

    @Autowired
    private RedisService redisService;

    public List<Student> toHelloPage() {
        School school = redisService.playJedis();
        return  demoMapper.toHelloPage();
    }
}
