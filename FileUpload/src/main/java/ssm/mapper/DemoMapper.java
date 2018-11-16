package ssm.mapper;

import ssm.bean.Skin;
import ssm.bean.Student;
import ssm.bean.UserPrizeNum;
import ssm.bean.Zero;

import java.util.List;

public interface DemoMapper {
    List<Student> toHelloPage();

    void toHelloPages(Student student);

    void insertZero(Zero zero);

    List<Zero> findZero();

    void insertSkin(Skin skin);

    void deletesSkin();

    List<Skin> findZeroSkin();

    List<UserPrizeNum> findNewNum(Long prizeId);

    void insetPickNum(UserPrizeNum userPrizeNum);
}
