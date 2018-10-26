package ssm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.bean.Student;
import ssm.mapper.DemoMapper;

import java.util.List;

@Service
public class DemoService {
    @Autowired
    private DemoMapper demoMapper;

    public List<Student> toHelloPage() {
        return  demoMapper.toHelloPage();
    }
}
