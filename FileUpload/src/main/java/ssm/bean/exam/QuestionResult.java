package ssm.bean.exam;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 学生对试卷中的问题作答后的得分结果
 */
@Getter
@Setter
@Accessors(chain = true)
public class QuestionResult {
    private Long id;         // ID
    private Long userId;     // 考试用户的 ID
    private Long examId;     // 考试的 ID
    private Long recordId;   // 考试记录的 ID
    private Long paperId;    // 试卷的 ID
    private Long questionId; // 题目的 ID
    private int    status;   // 题目正误 0:未处理 1:错误 2:正确 3:半对
    private double score;    // 得分
}
