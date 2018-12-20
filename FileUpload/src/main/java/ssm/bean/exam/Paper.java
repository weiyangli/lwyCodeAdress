package ssm.bean.exam;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.List;

/**
 * 试卷，就只有一个试卷 ID 是关键，在 exam_paper_question 中使用，表示题目属于某个试卷。
 */
@Getter
@Setter
@Accessors(chain = true)
public class Paper {
    private Long id;                // 试卷的 ID
    private Long userId;            // 创建试卷用户的 ID
    private int  type;              // 试卷类型: 0(普通试卷), 1(答题卡试卷), 2(作业试卷), 3(测评试卷), 4(调查问卷)
    private String title;           // 试卷的标题
    private double totalScore;      // 试卷总分
    private String paperUrl;        // 答题卡试卷文件的 URL
    private String answerUrl;       // 答题卡试卷答案的 URL
    private String detailsUrl;      // 答题卡试卷双向细目表的 URL
    private String paperFilename;   // 答题卡试卷文件的名称
    private String answerFilename;  // 答题卡试卷答案的名称
    private String detailsFilename; // 答题卡试卷双向细目表的名称
    private boolean subjective;     // 为 true 表示试卷有主观题，为 false 表示全是客观题

    private List<Question> questions = new LinkedList<>(); // 试卷的题目

    // 向试卷中增加题目
    public void addQuestion(Question question) {
        question.setPaperId(id);
        questions.add(question);
    }
}
