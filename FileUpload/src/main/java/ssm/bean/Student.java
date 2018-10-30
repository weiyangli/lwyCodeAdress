package ssm.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Setter
@Getter
@Accessors(chain = true)
public class Student implements Serializable {
    private Long studentId;      // 学生Id
    @NotBlank(message = "学生名称不能为空")
    private String userName;     // 学生姓名
    private String nickName;     // 昵称
    private String sex;          // 性别
    private int age;             // 年龄
}
