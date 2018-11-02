package ssm.handle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

/*【程序1】
题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？
1.程序分析： 兔子的规律为数列1,1,2,3,5,8,13,21....
*/
public class DaLuanDou {
    public static void main(String[] args) throws Exception{
        String s = "http://www.jfshare.com/view/product.html?productId=ze171108154749000726#!/";
        Document document = Jsoup.connect("http://www.jfshare.com/#!/")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3423.2 Safari/537.36")
                .get();
        // 将转到的文件写成一个txt文件
        File file = new File("C:\\Users\\47477\\Desktop\\baidu.html");
        Elements elements = document.getElementsByClass("goods");
        elements.forEach(element -> {
            Element tagA = element.getElementsByTag("a").first();
            String descript = tagA.getElementsByClass("descript").text();// 商品
            String vicename = tagA.getElementsByClass("vicename").text();// 运输详情
            String price = tagA.getElementsByClass("price").first().text();// 价值人名币
           /* String jifen = null;
            try {
                jifen = tagA.getElementsByClass("price").get(2).text(); // 价值积分
            }catch (Exception e) {
                jifen = "暂无积分信息";
                System.out.println("该商品没有积分价格!");
            }*/
            System.out.println(descript+"   "+vicename+"价值"+price);
            System.out.println("---------------------->>");
        });
        /*String body = document.body().toString();
        // 文件不存在创建
        if (!file.exists()) {
            file.createNewFile();
        }
        // 将从网页上爬到的内容写到 file
        PrintWriter pw = new PrintWriter(file);
        pw.append(body);*/
    }
}
