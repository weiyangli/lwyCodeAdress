package ssm.handle;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ssm.util.Utils;

public class Jsoup {
    public static void main(String[] args) throws  Exception{
        Document document= Utils.getDocument("http://lol.qq.com/data/info-heros.shtml");
        Element element = document.getElementsByClass("imgtextlist").first();
        Elements elements = element.getElementsByTag("li");
        elements.forEach(liElement -> {
            String titles = liElement.getElementsByTag("a").attr("title");
            System.out.println(titles.trim().replaceAll("","-"));
            /*System.out.println(titles[1]);
            if (titles.length >1) {
                System.out.println(titles[2]);
            }*/
        });
    }
}
