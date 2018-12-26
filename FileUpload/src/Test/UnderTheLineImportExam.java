import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class UnderTheLineImportExam {

    private static final int START_ROWS = 0;

   /* public static void ImportExam(InputStream inputStream) throws Exception {
        byte[] buffer = IOUtils.toByteArray(inputStream); // 输入流的缓存
        inputStream.close();

        // 1. 根据excel表数据创建考试
        ImportParams examRecordParams = new ImportParams();
        examRecordParams.setStartRows(START_ROWS);
        examRecordParams.setStartSheetIndex(0);
        List<ExamExcel> examExcels = ExcelImportUtil.importExcel(new ByteArrayInputStream(buffer), ExamExcel.class, examRecordParams);
        System.out.println(JSON.toJSONString(examExcels));

    }*/


    /*
     * 得到excel所有的列
     * */

    /**
     * 查询指定目录中电子表格中所有的数据
     *
     * @return
     */


    public static void main(String[] args) throws Exception {
        /*
        * HashMap和LinkedHashMap的区别:
        * HashMap放入数据会根据hashcode分布数据，所以数据放入hashmap后，
        * 再去取出来的时顺序和取出时不一致。
        * LinkedHashMap采用的时双向链表结构保证了数据的顺序
        * */
        int[][] arr = {{5, 7, 9}, {12, 14, 16, 18}, {23, 25, 36, 47}, {22, 54, 65, 15}, {22, 34}};
        // getAllByExcel("C:\\Users\\47477\\Desktop\\线下成绩导入模版.xls");
        Map<String, String> map = new LinkedHashMap<String, String>();

        map.put("a3", "aa");

        map.put("a2", "bb");

        map.put("b1", "cc");

        System.out.println(map.get("a3"));


        for (Iterator iterator = map.values().iterator(); iterator.hasNext();) {

            String name = (String) iterator.next();

            System.out.println(name);

        }
    }
}
