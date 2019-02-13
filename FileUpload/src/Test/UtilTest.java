import ssm.util.DateUtil;

import java.util.Date;


public class UtilTest {
    public static void main(String[] args) {
        Date date = DateUtil.getPreviousMonday(1);
        String day = DateUtil.formatDate(date,"yyyy-MM-dd E");
        System.out.println(day);
        System.out.println(DateUtil.getLastMonthLastDate());
    }

}
