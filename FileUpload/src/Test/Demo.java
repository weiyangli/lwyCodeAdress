import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import ssm.bean.School;
import ssm.bean.Student;
import ssm.service.DemoService;
import ssm.service.ExamImportService;
import ssm.service.RedisService;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Autowired
    private ExamImportService examImportService;

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
        //demoService.insertSkins();
        demoService.findZeroSkins();
    }
    /*
    * 测试spring Aop
    * */
    @Test
    public void testAop() {
        demoService.insertValue("张三");
    }

    /*
     * 测试生成excel
     * */
    @Test
    public void genExcel() {
        demoService.genPrizesExcel();
    }

    /**
     * 缓存穿透测试
     * https://www.cnblogs.com/zhujiabin/p/5404771.html 綫程池講解
     */
    @Test
    public void testRedis() throws  Exception{
        ExecutorService executorService= Executors.newFixedThreadPool(20);
        for (int i=0 ; i< 1000; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        redisService.testRedisBoom();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        }
    }

}
