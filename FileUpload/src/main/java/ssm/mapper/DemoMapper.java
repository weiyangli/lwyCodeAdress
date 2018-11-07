package ssm.mapper;

import ssm.bean.Skin;
import ssm.bean.Student;
import ssm.bean.Zero;

import java.util.List;

public interface DemoMapper {
    List<Student> toHelloPage();

    void toHelloPages();

    void insertZero(Zero zero);

    List<Zero> findZero();

    void insertSkin(Skin skin);

    void deletesSkin();
}
