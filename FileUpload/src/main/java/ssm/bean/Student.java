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
    @NotBlank(message = "学生Id不能为空")
    private Long studentId;      // 学生Id
    private String userName;     // 学生Id
    private String nickName;     // 上传的图片名
    private String sex;          // 性别
    private int age;             // 年龄
}
