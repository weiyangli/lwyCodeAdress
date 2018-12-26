package ssm.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ssm.bean.Result;
import ssm.bean.ResultConstant;
import ssm.bean.exam.ClazzStudent;
import ssm.bean.exam.Exam;
import ssm.bean.exam.ExamExcel;
import ssm.bean.exam.ExamRecord;
import ssm.bean.exam.Paper;
import ssm.bean.exam.Question;
import ssm.bean.exam.QuestionResult;
import ssm.mapper.ExamImportMapper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExamImportService {
    private static Logger logger = LoggerFactory.getLogger(PrizeService.class.getName());

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ExamImportMapper examImportMapper;

    private static final int FIRST_QUESTION_INDEX = 9;
    private static final int START_ROWS = 0;

    @Transactional
    public Result importExamDataByExcel(MultipartFile excelFile, Long userId) throws Exception {
        // 1. 用 easypoi 获取 excel 基础数据
        // 2. 根据基础数据保存试卷
        // 3. 根据基础数据保存考试
        // 4. 根据基础数据保存每个学生的考试记录
        // 5. 用 Workbook 处理获取 excel 表不固定列数据
        // 6. 保存试卷题目
        // 7. 保存学生每道题的作答结果

        Result result = new Result();
        // [1] 用 easypoi 获取 excel 基础数据
        List<ExamExcel> examExcels = getBaseData(excelFile.getInputStream());
        String examTitle = excelFile.getOriginalFilename().substring(0,excelFile.getOriginalFilename().lastIndexOf("."));
        if (examExcels.size() > 0) {
            // [2] 根据基础数据保存试卷
            Paper paper = new Paper();
            paper.setId(idWorker.nextId());
            paper.setUserId(userId);
            paper.setTitle(examTitle);
            paper.setTotalScore(examExcels.get(0).getScore());
            examImportMapper.insertPaper(paper);
            logger.info("试卷信息保存成功");

            // [3] 根据基础数据保存考试
            Exam exam = new Exam();
            double avg = examExcels.stream().mapToDouble(ExamExcel::getScore).average().getAsDouble();
            double max = examExcels.stream().mapToDouble(ExamExcel::getScore).max().getAsDouble();
            double min = examExcels.stream().mapToDouble(ExamExcel::getScore).min().getAsDouble();
            exam.setId(idWorker.nextId()).setPaperId(paper.getId()).setTitle(examTitle)
                    .setUserId(userId).setAvgScore(avg).setHighestScore(max).setLowestScore(min)
                    .setDifficulty(avg / examExcels.get(0).getScore());
            examImportMapper.insertExam(exam);
            logger.info("考试信息保存成功");

            // [4] 根据基础数据保存每个学生的考试记录
            // 查询班级的学生
            List<ClazzStudent> students = examImportMapper.findClazzStudents();
            Map<String, Long> studentMap = students.stream().collect(Collectors.toMap(ClazzStudent::getUsername, ClazzStudent::getId));
            List<ExamRecord> examRecords = new ArrayList<>();
            examExcels.forEach(examExcel -> {
                ExamRecord examRecord = new ExamRecord();
                examRecord.setId(idWorker.nextId());
                examRecord.setUserId(studentMap.get(examExcel.getUsername()));
                examRecord.setClazzId(students.get(0).getClazzId());
                examRecord.setNickname(examExcel.getNickname());
                examRecord.setStatus(3); // 已批改
                examRecord.setScore(examExcel.getScore());
                examRecord.setSubjectScore(examExcel.getSubjectScore());
                examRecord.setObjectScore(examExcel.getObjectScore());
                examRecord.setCheckedStatus(true);
                examRecord.setUsername(examExcel.getUsername());
                examRecord.setRank(examExcel.getClazzRanking());
                examRecord.setGradeRank(examExcel.getGradeRanking());
                examRecords.add(examRecord);
            });
            Map<String, Long> map = examRecords.stream().collect(Collectors.toMap(ExamRecord::getUsername, ExamRecord::getId));
            if (examRecords.size() > 0) {
                examImportMapper.insertExamRecord(examRecords);
            }

            // [5] 用 Workbook 处理获取 excel 表不固定列数据
            Workbook rwb = null;
            try {
                rwb = new XSSFWorkbook(excelFile.getInputStream());
            } catch (Exception e) {
                logger.error("获取文件流失败");
            }
            Sheet rs = rwb.getSheetAt(0);
            // 计算总行数
            int rows = rs.getLastRowNum();
            // 计算总列数
            int cols = rs.getRow(0).getPhysicalNumberOfCells();

            // [6] 保存试卷题目
            List<Question> questionList = new ArrayList<>();
            int num = 1;
            for (int j = FIRST_QUESTION_INDEX; j < cols; j++) {
                if (j % 2 != 0) {
                    Question question = new Question();
                    question.setPaperId(paper.getId()).setQuestionId(idWorker.nextId())
                            .setTotalScore(Double.parseDouble(rs.getRow(1).getCell(j).toString()))
                            .setPosition(num);
                    questionList.add(question);
                    num++;
                }
            }
            if (questionList.size() > 0) {
                examImportMapper.insertExamQuestions(questionList);
            }

            // [7] 保存学生每道题的作答结果
            List<QuestionResult> questionResults = new ArrayList<>();
            for (int i = 1; i <= rows; i++) {
                int questionNum = 0;
                for (int j = FIRST_QUESTION_INDEX; j < cols; j++) {
                    QuestionResult questionResult = new QuestionResult();
                    if (j % 2 == 0) {
                        questionResult.setId(idWorker.nextId()).setExamId(exam.getId()).setPaperId(paper.getId())
                                .setQuestionId(questionList.get(questionNum).getQuestionId())
                                .setUserId(studentMap.get(rs.getRow(i).getCell(1).toString())).setScore(Double.parseDouble(rs.getRow(i).getCell(j).toString()))
                                .setRecordId(map.get(rs.getRow(i).getCell(1).toString()));
                        questionNum++;
                        questionResults.add(questionResult);
                    }
                }
            }
            if (questionResults.size() > 0) {
                examImportMapper.insertQuestionResult(questionResults);
            }
            result.initMe(ResultConstant.SUCCESS);
        } else {
            result.initMe(ResultConstant.IMPORT_ERROR);
        }
        return result;
    }

    /*
     * 获取考试的基本数据
     * */
    /*
    * @RestController 和 @Controller 的区别:
    * @RestController 注解相当于@ResponseBody ＋ @Controller 使用该注解整个类都不可以
    * 返回jsp/html 返回的数据都被当成了一个json串。
    * @Controller return 是会自动将返回的参数当成一个页面解析跳转，想要返回json串可以在
    * 方法体上添加 @ResponseBody 注解 ，使用@Contrller 相比 @RestController 比较灵活
    * */
    public static List<ExamExcel> getBaseData(InputStream inputStream) throws Exception {
        byte[] buffer = IOUtils.toByteArray(inputStream); // 输入流的缓存
        ImportParams examRecordParams = new ImportParams();
        examRecordParams.setStartRows(START_ROWS);
        examRecordParams.setStartSheetIndex(0);
        List<ExamExcel> examExcels = ExcelImportUtil.importExcel(new ByteArrayInputStream(buffer), ExamExcel.class, examRecordParams);
        System.out.println(JSON.toJSONString(examExcels));
        return examExcels;
    }
}
