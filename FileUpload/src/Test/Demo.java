import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ssm.bean.School;
import ssm.bean.Student;
import ssm.service.DemoService;
import ssm.service.RedisService;

import java.util.List;

/*
* 框架测试类
* */
@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:config/application.xml"})
public class Demo {
    @Autowired
    private RedisService redisService;

    @Autowired
    private DemoService demoService;

    @Test
    public  void demoTest () {
        School school = redisService.playJedis();
        List<Student> students = demoService.toHelloPage();
        Student student = null;
        if (students.size() > 0) {
            student = students.get(0);
            System.out.println("我是"+school.getName()+"的一名高中生我叫"+student.getNickName()+"今年"+student.getAge());
        }
    }
    @Test
    public void insertSkin() throws Exception{
        demoService.insertSkins();
    }
    /*
    * 测试spring Aop
    * */
    @Test
    public void testAop() {
        demoService.insertValue();
    }
}
