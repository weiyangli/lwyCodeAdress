package ssm.handle;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.bean.Skin;
import ssm.bean.Student;
import ssm.bean.Zero;
import ssm.mq.MessageProducer;
import ssm.service.DemoService;
import ssm.service.PrizesThread;
import ssm.util.Utils;

import javax.validation.Valid;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class.getName());

    @Autowired
    private DemoService demoService;

    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/")
    public String index() {
        System.out.println("劲来了");
        return "/page/html/index.html";
    }

    /**
     * @param Id
     * @return
     */
    @RequestMapping(value = Urls.PAGE_DEMO_REST, method = RequestMethod.GET)
    @ResponseBody
    public List<Zero> toHelloPage(@PathVariable(required = false) long Id) {
        logger.info("待操作数据Id为" + Id);
        // List<Student> students = demoService.toHelloPage();
        List<Zero> zeros = demoService.findZero();
        // 抽奖
        for(Zero zero:zeros) {
            // 操作消息队列,使用mq通道进行
            messageProducer.pickPrize(zero.getName() + " " + zero.getTitle());
        }
        return zeros;
    }

    /**
     * @param student
     * @param bindingResult
     * @return
     */
    @PostMapping(Urls.PAGE_DEMO_CREATE)
    @ResponseBody
    public String toHelloPages(Student student, BindingResult bindingResult) {
        demoService.toHelloPages(student);
        return student.getUserName();
    }

    /**
     * @return
     * @throws Exception
     */
    @RequestMapping(value = Urls.HERO_LOL_JSON, method = RequestMethod.GET)
    @ResponseBody
    public String saveHero() throws Exception {
        File file = new File("C:\\Users\\47477\\Desktop\\lwyCodeAdress\\FileUpload\\src\\main\\resources\\json\\hero.json");
        String bookJson = FileUtils.readFileToString(file, "UTF-8");
        demoService.saveHero(bookJson);
        return "成功!";
    }
    // 电子书包转换服务
    /*
     * 1. 根据转换文件是零时还是永久走不同的url,先判断文件是否存在(是否已经上传成功)，在判断该文件类型是否需要去转换
     * 2. 如果需要转换判断文件的类型，要转换后成pdf还是MP4，判断好以后将要转换的路径放到消息队列生产队列上，消费者拿到队列后
     * 调用转换服务，先跟据待转化的文件生成一个zookeeper的节点key,利用线程同步连接zk,连接zk后开始转换文件，文件转换完以后释放
     * zk，返回转换后的文件预览路径。
     * */

    /**
     * @param count
     * @return
     */
    @RequestMapping(value = Urls.HERO_PICK_SKIN, method = RequestMethod.GET)
    @ResponseBody
    public  List<Skin> toHelloPage(@RequestParam int count) {
        logger.info("想要抽取" + count);
        return demoService.extractSkin(count);
    }
}
