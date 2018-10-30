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

    @Autowired
    private EsService esService;

    @Autowired
    private IdWorker idWorker;

    public List<Student> toHelloPage() {
        School school = redisService.playJedis();
        esService.esAddData(school,school.getId());
        return  demoMapper.toHelloPage();
    }

    public void toHelloPages(Student student) {
        if(student.getStudentId() == 0) {
            student.setStudentId(idWorker.nextId());
        }
        demoMapper.toHelloPages();
    }
}
