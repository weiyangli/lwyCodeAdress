package ssm.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 学校类
 */
@Getter
@Setter
@Document(indexName = "school", type = "school")
@Accessors(chain = true)
public class School {
    private Long id;      // 学校 ID
    private Long adminId; // 管理员帐号的 ID

    @NotBlank(message="学校名字不能为空(name)")
    private String  name;             // 全称
    private String  abbreviationName; // 简称
    private String  host;             // 站点域名
    private int     port;             // 站点端口

    private Integer foundingYear;     // 建校时间

    private String  contactPerson;    // 联系人
    private String  contactPhone;     // 联系电话
    private String  contactEmail;     // 联系邮件

    private Address address;          // 学校地址
    private boolean enabled;          // 是否可用
    private boolean messageEnabled;   // 是否开启短信登录
    private String  messageTemplate;  // 短信模板
    private List<String> educationTypes; // 教育类型
    private String educationTypesAsText; // 数组教育类型拼起来的字符串，保存到数据库
    private int    teacherCount; // 老师的数量
    private int    studentCount; // 学生的数量

    private String title;      // 网站标题
    private String logoUrl;    // 网站 Logo
    private String imageUrl;   // 网站主题图
    private String icpLicense; // 网站备案
    private String links;      // 快速链接，使用数组的 JSON 字符串
    private boolean libraryEnabled; // 是否启用数字图书馆

    public void setEducationTypes(List<String> educationTypes) {
        this.educationTypes = educationTypes;
        this.educationTypesAsText = StringUtils.join(educationTypes, ",");
    }

    public void setEducationTypesAsText(String educationTypesAsText) {
        if (this.educationTypes == null) {
            this.educationTypes = new LinkedList<>();
        }

        this.educationTypesAsText = educationTypesAsText;
        this.educationTypes.clear();
        this.educationTypes.addAll(Arrays.asList(StringUtils.split(educationTypesAsText, ",")));
    }
}
