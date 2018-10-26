import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ssm.bean.School;
import ssm.service.RedisService;

/*
* 框架测试类
* */
@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:config/application.xml"})
public class Demo {
    @Autowired
    private RedisService redisService;

    @Test
    public  void demoTest () {
        School school = redisService.playJedis();
    }
}
