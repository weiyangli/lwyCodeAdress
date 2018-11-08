package ssm.task;

import org.springframework.beans.factory.annotation.Autowired;
import ssm.service.IdWorker;

public class DemoSpringTask {

    @Autowired
    private IdWorker idWorker;

    public void testDemo () {
        Long taskId = idWorker.nextId();
        System.out.println("我是定时任务"+taskId+" I am running");
    }
}
