package ssm.mapper;

import ssm.bean.exam.ClazzStudent;
import ssm.bean.exam.Exam;
import ssm.bean.exam.ExamRecord;
import ssm.bean.exam.Paper;
import ssm.bean.exam.Question;
import ssm.bean.exam.QuestionResult;

import java.util.List;

public interface ExamImportMapper {
    void insertPaper(Paper paper);

    void insertExam(Exam exam);

    void insertExamRecord(List<ExamRecord> examRecords);

    void insertExamQuestions(List<Question> questionList);

    void insertQuestionResult(List<QuestionResult> questionResults);

    List<ClazzStudent> findClazzStudents();
}
