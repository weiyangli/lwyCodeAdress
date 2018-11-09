import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class Dom4j {
    public static void main(String[] args) throws  Exception{
        SAXReader reader = new SAXReader();
        File file = new File("C:/Users/47477/Desktop/demo.xml");
        Document document = reader.read(file);
        Element root = document.getRootElement();
        List<Element> childElements = root.elements();
        for (Element element:childElements) {
            element.elementText("title");
            System.out.println("author" + element.elementText("author"));
        }
        genXml();
    }

    public static void genXml() throws Exception{
        Document doc = DocumentHelper.createDocument();
        // 添加跟节点
        Element books = doc.addElement("heros");
        // 添加子元素，添加属性
        Element child1 = books.addElement("hero");
        child1.addAttribute("Id","007");
        Element child1_2 =  child1.addElement("name");
        child1_2.setText("放逐之刃");
        Element child1_3 =  child1.addElement("area");
        child1_3.setText("艾欧里亚");

        // 第二个子元素
        Element child2 = books.addElement("hero");
        child2.addAttribute("Id","008");
        Element child2_2 =  child2.addElement("name");
        child2_2.setText("易");
        Element child2_3 =  child2.addElement("area");
        child2_3.setText("浮点数格式的");

        // 将xml文件输出
        //实例化输出格式对象
        File file = new File("C:/Users/47477/Desktop/hero.xml");
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置输出编码
        format.setEncoding("UTF-8");
        //创建需要写入的File对象
        //生成XMLWriter对象，构造函数中的参数为需要输出的文件流和格式
        XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
        //开始写入，write方法中包含上面创建的Document对象
        writer.write(doc);
    }
}
