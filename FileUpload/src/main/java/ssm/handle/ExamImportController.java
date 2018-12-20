package ssm.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ssm.service.ExamImportService;


@Controller
public class ExamImportController {
    @Autowired
    private ExamImportService examImportService;

    @PostMapping(Urls.EXAM_IMPORT)
    @ResponseBody
    public String toHelloPages( MultipartFile excel) throws Exception{
        examImportService.importExamDataByExcel(excel,1111l);
        return "success";
    }
}
