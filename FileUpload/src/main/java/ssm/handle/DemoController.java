package ssm.handle;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.bean.Student;
import ssm.service.DemoService;

import java.util.List;

@Controller
public class DemoController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class.getName());
    @Autowired
    private DemoService demoService;

    // URL: http://localhost:8080/page/demo/rest
    @RequestMapping(Urls.PAGE_DEMO_REST)
    @ResponseBody
    public String toHelloPage(@PathVariable long Id) {
        System.out.println(Id);
        List<Student> students =  demoService.toHelloPage();
        return JSON.toJSONString(students);
    }

    // 电子书包转换服务
    /*
    * 1. 根据转换文件是零时还是永久走不同的url,先判断文件是否存在(是否已经上传成功)，在判断该文件类型是否需要去转换
    * 2. 如果需要转换判断文件的类型，要转换后成pdf还是MP4，判断好以后将要转换的路径放到消息队列生产队列上，消费者拿到队列后
    * 调用转换服务，先跟据待转化的文件生成一个zookpeer的节点key,利用线程同步启动zk,zk启动后开始转换文件，文件转换完以后释放
    * zk，返回转换后的文件预览路径。
    *
    * */
}