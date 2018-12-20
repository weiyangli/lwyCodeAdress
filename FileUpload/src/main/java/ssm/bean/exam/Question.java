package ssm.bean.exam;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目，为了简单直观起见，把题型，学科，教学要求等都保存中文字符串到题目表里，
 * 这样查询的时候就不需要进行多个表关联查询了。难度没有保存中文，而是数字是为了可以
 * 抽取大于某个难度的题目。
 * <p>
 * 提示:
 * 1. 因为 qa 中也用了 Question 这个类，导致 MyBatis 的别名 Question 冲突，
 * 所以考试系统中题目的别名定义为 ExamQuestion，在 Mapper 中使用。
 * 2. 材料题的小题不能设置知识点编码和章节编码，否则抽题时抽中小题，没抽中对应的材料题就出错了
 * 3. 材料题的小题需要设置 position，确定在材料题中的位置，不能随意打乱小题的位置
 * 4. 单选题、多选题、判断题、填空题、问答题都可以归为一类，都可以用选项来存储，只是展示的时候不一样而已
 * * 单选题的选项只有一个能被选中
 * * 多选题的选项能够多个被选中
 * * 判断题是只有 2 个选项的单选题
 * * 填空题的每一个空对应一个选项
 * * 问答题有一个选项
 * 5. 材料题的大题只有题干，没有选项，但是它的小题可以是单选题、多选题、判断题、问答题 (目前设计的不支持填空题)
 * 6. 一个题目可能属于多个章节，题目章节关系存储在表     exam_question_chapter
 * 7. 一个题目可能属于多个知识点，题目知识点关系存储在表 exam_question_knowledge_point
 */
@Getter
@Setter
@Accessors(chain = true)
@Alias("ExamQuestion")
public class Question {
    // 题目类型
    public static final int BASE_TYPE_SINGLE_CHOICE = 1; // 单选题
    public static final int BASE_TYPE_MULTI_CHOICE = 2; // 多选题
    public static final int BASE_TYPE_TFNG = 3; // 判断题: true(是), false(否), not given(未提及)
    public static final int BASE_TYPE_ESSAY_QUESTION = 4; // 问答题
    public static final int BASE_TYPE_FILL_IN_THE_BLANK = 5; // 填空题
    public static final int BASE_TYPE_COMPREHENSION = 6; // 材料题
    public static final int BASE_TYPE_HOMEWORK = 7; // 作业题
    public static final int BASE_TYPE_DESCRIPTION = 8; // 描述

    // 教学要求
    public static final int TEACH_REQUIREMENT_KNOW = 1; // 了解
    public static final int TEACH_REQUIREMENT_UNDERSTAND = 2; // 理解
    public static final int TEACH_REQUIREMENT_MASTER = 3; // 掌握
    public static final int TEACH_REQUIREMENT_USE = 4; // 运用
    public static final int TEACH_REQUIREMENT_COMPREHENSION = 5; // 综合

    // [1] 题目本身的内容，和试卷无关，关联表为 exam_question
    private Long id;                 // 题目的 ID
    private String stem;               // 题干
    private String answer;             // 答案
    private String analysis;           // 解析
    private String phase;              // 学段: 小学、初中、高中
    private String subject;            // 学科: 语文、数学
    private int baseType;           // 基本题型: 未知(0)、单选题(1)、多选题(2)、判断题(3)、问答题(4)、填空题(5)、材料题(6)、作业题(7)、描述(8)
    private String extendType;         // 扩展题型: 实验题、解答题、作图题，只是基本题型的别名
    private String classification;     // 试题分类: 期中、期末、单元测试、专项练习
    private int teachRequirement;   // 教学要求: 未知(0)、了解(1)、理解(2)、掌握(3)、运用(4)、综合(5)
    private int difficulty;         // 题目难度: 未知(0)、容易(1), 较易(2), 一般(3), 较难(4), 困难(5)
    private String region;             // 适用地区
    private String origin;             // 试题来源
    private String creator;            // 创建者
    private String reviser;            // 最后修改人
    private int auditStatus;        // 审核状态: 0(未处理), 1(通过), 2(未通过)
    private int usedCount;          // 使用次数
    private int publishYear;        // 发布年代
    private Long parentId = 0L;      // 材料题时的材料题的 ID，普通题时 parent_id 为 0
    private Date createdTime;        // 创建时间
    private Date updatedTime;        // 更新时间
    private Date errorQuestionTime;  // 错题时间(用于错题本)
    private int errorCount;         // 学生错题次数
    private int purpose;            // 题目用途: 题库(0), 作业题(不需要确认: 1，需要确认: 2), 课堂评测老师创建的题目(3)，问卷题目(4)
    //          答题卡描述的 purpose 为此描述所在组的题目的类型，如单选题(1)、多选题(2) 等
    //          答题卡中非描述题的 purpose 为 10

    private boolean objective;         // true 表示客观题，false 表示主观题: 查询的时候使用
    /*  private QuestionStatistics statistics; // 题目回答统计(作业讲评时使用)

      private Set<Chapter>        chapters        = new HashSet<>(); // 所属章节
      private Set<KnowledgePoint> knowledgePoints = new HashSet<>(); // 所属知识点

      private List<QuestionOption> options      = new LinkedList<>(); // 题目的选项，关联的表为 exam_question_option*/
    private List<Question> subQuestions = new LinkedList<>(); // 材料题的小题，小题的 parentId 不为 0

    // [2] 试卷的题目，关联表为 exam_paper_question
    // 试卷的题目，包含的信息有如题目在某个试卷里的位置、分值、分组 (例如属于单选题) 等。
    // 分组的值可以随意，只要同一个组的 group 值相同就行。
    private Long paperId;    // 试卷的 ID
    private Long questionId; // 问题的 ID
    private int position;   // 题目在试卷里的位置，材料题的小题在材料题里的位置
    private int groupSn;    // 分组顺序，例如属于单选题，顺序表示在试卷中的位置
    private double score;      // 题目的分值
    private double totalScore; // 题目的满分 (题目是试卷中的大题或材料题时使用)
    private int status;     // 题目正误 0:未处理 1:错误 2:正确 3:半对
    private String snLabel;    // 试卷中题目的序号

    private boolean deleted; // 是否被删除，如果为 true，则表示要从数据库里删除，主要用于前端传给后端时用


    private QuestionResult result = new QuestionResult(); // 问题的批改结果

    public Question() {
    }

    /**
     * 创建简单的题目
     */
    public Question(long id, String stem, String analysis, int baseType) {
        this.id = id;
        this.stem = stem;
        this.analysis = analysis;
        this.baseType = baseType;
    }

    /**
     * 创建试卷的题目
     */
    public static Question createPaperQuestion(long paperId, long questionId, int position, double score, int groupSn) {
        Question question = new Question();
        question.setPaperId(paperId);
        question.setId(questionId);
        question.setPosition(position);
        question.setScore(score);
        question.setGroupSn(groupSn);

        return question;
    }


    /**
     * 给材料题增加小题
     *
     * @param subQuestion 材料题的小题
     */
    public void addSubQuestion(Question subQuestion) {
        subQuestion.setParentId(id);
        subQuestions.add(subQuestion);
    }

}
