package ssm.bean.exam;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 考试记录，保存学生考试的记录，同一个试卷可以考多次，考试记录用来确定某一次考试
 * 默认发布考试的时候，给该班级下的所有学生都创建一份考试记录
 */
@Getter
@Setter
@Accessors(chain = true)
public class ExamRecord {
    private Long id;               // 试卷记录的 ID
    private Long userId;           // 考试用户的 ID
    private Long clazzId;          // 班级的 ID
    private Long examId;           // 考试的 ID
    private Long paperId;          // 试卷的 ID
    private int  status;           // 状态: 未开始(0), 已作答(1)，已提交(2)，未提交(3)，已批改(4)
    private int  frontStatus;      // 前端显示的状态: 未开始(0), 开始考试(1)，继续考试(2)，批阅中(3)，已批改(4)
    private String username;       // 账号
    private String nickname;       // 姓名
    private double score;          // 考试得分
    private double subjectScore;   // 主观题得分
    private double objectScore;    // 客观题得分
    private Date   submittedTime;  // 提交时间
    private boolean checkedStatus; // 学生是否已经查阅该次考试: 未查阅(false), 已查阅(true)
    private long    usedTime;      // 考试已作答时间 (秒)
    private int     rank;          // 学生排名
    private int     gradeRank;     // 年级排名
    private float   accuracy;      // 准确率

    private long  remainedTime; // 剩余时间（秒），考试结束时间和剩余考试持续时间中选取最小的一个
    private Exam  exam;         // 考试
    private Paper paper;        // 试卷

    List<QuestionResult> questionResults; // 每一道题目的得分

}
