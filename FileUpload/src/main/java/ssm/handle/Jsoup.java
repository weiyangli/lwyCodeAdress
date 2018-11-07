package ssm.handle;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ssm.util.Utils;

public class Jsoup {
    public static void main(String[] args) throws  Exception{
        // 一共访问18个页面
       /* Document document= Utils.getDocument("http://lol.52pk.com/skinlist_3851_4_1.shtml");
        Element element = document.getElementsByClass("listBox").first();
        Elements elements = element.getElementsByTag("dl");
        System.out.println(elements.size());
        for(Element element2:elements) {
            String pngUrl = element2.getElementsByTag("dt")
                    .first().getElementsByTag("a").first().getElementsByTag("img").attr("src");
            String name = element2.getElementsByTag("dd").first().getElementsByTag("a").first().text();
            String level = element2.getElementsByTag("dd").select("p").first().getElementsByTag("a").first().text();
            String price = element2.getElementsByTag("dd").select("p").get(1).text().replaceAll(" ","").split("购买")[0].replace("价格：","");
            System.out.println(name+"图片为"+pngUrl+"级别为"+level+"price"+price);
        }
*/
       // System.out.println(document.body());
    }
}
