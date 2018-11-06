package ssm.handle;

import org.jsoup.nodes.Document;
import ssm.util.Utils;

public class Jsoup {
    public static void main(String[] args) throws  Exception{
        Document document= Utils.getDocument("https://gy.fang.anjuke.com/loupan/all/d463/");
        System.out.println(document.body());
    }
}
