import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
/*
*  字符流存在缓冲区，会将数据暂时存在缓冲区，当执行程序没有关闭流的时候，
*  文件数据还是在缓冲区没有输出到文件，当 out.close() 关闭流的时候，会将缓存中的数据
*  输出到目标文件，然后关闭流。如果想在不关闭流的情况下输出文本，可以使用Write.flush()方法。
* */
public class TestStream {
        public static void main(String[] args){ // 异常抛出，  不处理
            // 第1步：使用File类找到一个文件
            File f = new File("C:\\Users\\47477\\Desktop\\text.txt");// 声明File 对象
            // 第2步：通过子类实例化父类对象
            Writer out = null;
            // 准备好一个输出的对象
            try{
                out = new FileWriter(f);
            // 通过对象多态性进行实例化
            // 第3步：进行写操作
            String str = "Hello World!!!";
            // 准备一个字符串
            out.write(str);
            }catch (Exception e) {
                e.printStackTrace();
            }
            // 将内容输出
            // 第4步：关闭输出流
            //out.close();
            // 此时没有关闭
        }
}
