package ssm.bean.exam;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/*
* 用于导出考试excel
* */
@Getter
@Setter
@Accessors(chain = true)
public class ExamExcel {
    @Excel(name="班级名称")
    private String clazzName;       // 班级名称
    @Excel(name="学号")
    private String username;        // 账号
    @Excel(name="学生姓名")
    private String nickname;        // 学生名称
    @Excel(name="班级排名")
    private int clazzRanking;      // 班级排名
    @Excel(name="年级排名")
    private int gradeRanking;      // 年级排名
    @Excel(name="试卷满分")
    private int totalScore;         // 试卷满分
    @Excel(name="学生总得分")
    private double score;           // 学生总得分
    @Excel(name="学生客观题总得分")
    private double objectScore;     // 客观题得分
    @Excel(name="学生主观题总得分")
    private double subjectScore;    // 主观题得分
}
