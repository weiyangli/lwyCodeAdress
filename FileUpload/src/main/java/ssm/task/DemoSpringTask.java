package ssm.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ssm.service.DemoService;
import ssm.service.IdWorker;

public class DemoSpringTask {

    private static Logger logger = LoggerFactory.getLogger(DemoSpringTask.class.getName());
    @Autowired
    private IdWorker idWorker;

    public void testDemo () {
        Long taskId = idWorker.nextId();
        //System.out.println("我是定时任务"+taskId+" I am running");
    }

    /*
    * 定时抽取奖品
    * */
    public void startPickPrize(){
        logger.info("定时抽取奖品号码接口启动");
    }
}
