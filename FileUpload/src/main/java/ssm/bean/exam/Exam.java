package ssm.bean.exam;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 考试，包含试卷、班级、以及考试相关的信息组成，例如考试时间、是否允许查看答案、是否打乱题目顺序、是否打乱选项顺序，
 * 考试时间为在 start_time 和 end_time 之间，最多考 duration 分钟，允许考几次等。
 */
@Getter
@Setter
@Accessors(chain = true)
public class Exam {
    private Long id;          // 考试的 ID
    private Long paperId;     // 试卷的 ID
    private Long clazzId;     // 班级的 ID
    private String clazzName; // 班级名称
    private Long userId;      // 创建考试用户的 ID

    @NotNull(message = "考试名称不能为空")
    private String title;   // 考试名称

    private String subject; // 科目
    private Date startTime; // 考试开始时间
    private Date endTime;   // 考试结束时间
    private int duration;   // 考试持续时间, 单位为分钟, 例如 2 小时
    private int times;      // 允许考试的次数
    private boolean questionShuffled;       // 是否打乱问题
    private boolean questionOptionShuffled; // 是否打乱问题的选项
    private Date createdTime;        // 创建时间

    private int studentCount;   // 学生数量， 默认为0，发布考试的时候设置为班级人数
    private int checkedCount;   // 已查数量
    private int handedCount;    // 提交数量
    private int correctedCount; // 已批数量

    private boolean answerPublished; // 是否已公布答案，true：已公布 false：未公布
    private boolean published;       // 是否已发布考试，true：已发布 false：未发布，只有发布过的考试，学生才能看见
    private boolean finishedCorrection; // 是否已经批改完试卷

    private double lowestScore;  // 最低分
    private double highestScore; // 最高分
    private double avgScore;     // 平均分
    private double passRate;     // 及格率
    private double reliability;  // 信度
    private double validity;     // 效度
    private double difficulty;   // 难度
    private double discrimination; // 区分度

    private Paper paper; // 试卷

    /**
     * 缺考人数 = 学生人数 - 查看过试卷的人数
     */
    public int getAbsentCount() {
        return this.studentCount - this.checkedCount;
    }

    /**
     * 完成率（已提交学生人数 / 学生总人数 * 100）
     */
    public int getCompletedRate() {
        if (this.studentCount > 0) {
            return Math.round(this.handedCount / ((float) this.studentCount) * 100);
        }
        return 0;
    }

    // 格式化开始时间，去掉年和秒
    public String getStartTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(this.startTime);
    }

    // 格式化结束时间，去掉年和秒
    public String getEndTimeStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(this.endTime);
    }
}
