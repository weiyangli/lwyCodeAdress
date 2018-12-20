import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.usermodel.Workbook;
import ssm.bean.Person;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 字节流和字符流：如果数据的文件使用windows的txt文件能够打开使用字符流:
 * 及Reader，writer
 * 字节流用来处理系统不易读取其中的数据的流，比如其他格式的流。
 * OutputStream 是输出流用于将数据写入到文件，再将文件输出到客户端
 * InputStream 是输入流用于将文件读成流的形式到程序中处理
 * */
public class TextExport {
    public static void exportPerson() {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person();
        person1.setId(11111111111111111l);
        person1.setName("JASCK");
        person1.setAge(11);
        person1.setSex(1);
        Person person2 = new Person();
        person2.setId(6453546254l);
        person2.setName("jay");
        person2.setAge(11);
        person2.setSex(1);
        personList.add(person1);
        personList.add(person2);
        Person person3 = new Person(435421353l, "tony", 12, 2);
        personList.add(person3);
        //导出操作
        ExportParams exportParams = new ExportParams("花名册", "草帽一伙");
        exportParams.setCreateHeadRows(true);
        OutputStream os = null;
        try {
            os = new FileOutputStream("C:\\Users\\47477\\Desktop\\test.xlsx", true);
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Person.class, personList);
            workbook.write(os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dealJava8() {
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        List<Integer> a = list.stream().map(x -> x+2).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(a));
    }

    public static void main(String[] args) {
        dealJava8();
    }
}
