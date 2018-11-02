package ssm.mapper;

import ssm.bean.Student;
import ssm.bean.Zero;

import java.util.List;

public interface DemoMapper {
    List<Student> toHelloPage();

    void toHelloPages();

    void insertZero(Zero zero);
}
