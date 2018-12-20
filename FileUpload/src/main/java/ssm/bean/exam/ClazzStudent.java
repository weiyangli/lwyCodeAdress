package ssm.bean.exam;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * 学生的类，一个学生学习他属于的班级的所有学科
 */
@Getter
@Setter
@Accessors(chain = true)
public class ClazzStudent {
    private Long id; // 学生 ID
    private Long schoolId; // 学校 ID
    private Long clazzId;  // 班级Id

    @Excel(name="姓名")
    private String nickname; // 姓名，也就是昵称

    @Excel(name="账号")
    private String username; // 账号

    @Excel(name="密码")
    private String password; // 账号密码

    @Excel(name="班级名称")
    private String clazzName; // 班级名称

    @Excel(name="班级编码")
    private String clazzCode;  // 班级编码: 目前 Excel 里没有这一列

    @Excel(name="序号")
    private Long sn; // Excel 中的序号

    private String  avatar;  // 头像
    private boolean enabled; // 是否可用
    private int gender;      // 性别


    // 家长
    private Long parentId;         // 家长 ID
    private String parentUsername; // 家长账号

    // 辅助字段
    private boolean leader;   // 是否组长
    private Long groupId;     //  当前分组ID
    private String groupName; // 当前分组名称

    // 为了导入时方便，使用 schoolId+username 创建 equals 和 hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClazzStudent student = (ClazzStudent) o;
        return Objects.equals(schoolId, student.schoolId) && Objects.equals(username, student.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolId, username);
    }
}
