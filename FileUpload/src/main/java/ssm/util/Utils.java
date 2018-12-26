package ssm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ssm.bean.Zero;
import ssm.handle.PaChong;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 常用功能的工具类，例如计算 MD5, Base64，UUID 等
 */
public final class Utils {
    private static Logger logger = LoggerFactory.getLogger(Utils.class);
    private static PasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private static PrettyTime prettyTime = new PrettyTime(Locale.CHINA);

    /**
     * BindingResult 中的错误信息很多，对用户不够友好，使用 getBindingMessage()
     * 提取对用户阅读友好的定义验证规则 message.
     *
     * @param result 验证的结果对象
     * @return 验证规则 message
     */
    public static String getBindingMessage(BindingResult result) {
        StringBuffer sb = new StringBuffer();

        for (FieldError error : result.getFieldErrors()) {
            // sb.append(error.getField() + " : " + error.getDefaultMessage() + "\n");
            sb.append(error.getDefaultMessage() + "\n");
        }

        return sb.toString();
    }

    /**
     * 计算字符串的 MD5.
     *
     * @param text 需要计算 MD5 的字符串
     * @return 返回字符串的 MD5
     */
    public static String md5(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 计算文件的 MD5.
     * MD5 包含 16 进制表示的 10 个字符: 0-9, a-z
     *
     * @param file 需要计算 MD5 的文件
     * @return 返回文件的 MD5，如果出错，例如文件不存在则返回 null
     */
    public static String md5(File file) {
        try (InputStream in = new FileInputStream(file)) {
            return DigestUtils.md5DigestAsHex(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 对字符串 text 进行 Base64 编码.
     * Base64 有 64 个字符: 0-9, a-z, A-Z, +, /
     * 等号 = 用于补齐.
     *
     * @param text 要进行编码的字符串
     * @return 返回使用 Base64 编码后的字符串
     */
    public static String base64(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解码 Base64 编码的字符串 base64Text.
     *
     * @param base64Text Base64 编码的字符串
     * @return 返回源字符串
     */
    public static String unbase64(String base64Text) {
        return new String(Base64.getDecoder().decode(base64Text), StandardCharsets.UTF_8);
    }

    /**
     * 对字符串 text 进行 URL Safe 的 Base64 编码: +, /, =, 被置换为 -, _, *, 只包含 64 个 URL safe 的字符: 0-9, a-z, A-Z, -, _
     * 注意: Base64.getUrlEncoder() 编码后的 Base64 结果还有 =，不能使用
     * <p>
     * 系统中有一些值使用 BASE64 编码后存储在 COOKIE 中, 当编码后的字符串最后有一个或者两个等号(=)时,
     * 使用 Request.getCookies().getValue() 会丢失等号, 再用 BASE64 解码时产生错误.
     *
     * @param text 要进行编码的字符串
     * @return 返回使用 URL Safe Base64 编码后的字符串
     */
    public static String base64UrlSafe(String text) {
        String base64Text = Utils.base64(text);
        base64Text = base64Text.replace('+', '-');
        base64Text = base64Text.replace('/', '_');
        base64Text = base64Text.replace('=', '*');

        return base64Text;
    }

    /**
     * 解码 URL Safe 的 Base64 编码的字符串 urlBase64Text.
     *
     * @param urlBase64Text URL Safe 的 Base64 编码的字符串
     * @return 返回源字符串
     */
    public static String unbase64UrlSafe(String urlBase64Text) {
        urlBase64Text = urlBase64Text.replace('-', '+');
        urlBase64Text = urlBase64Text.replace('_', '/');
        urlBase64Text = urlBase64Text.replace('*', '=');

        return Utils.unbase64(urlBase64Text);
    }

    /**
     * 生成 UUID，格式为 1E87E000-92C0-4660-B00D-FF92B37B0A7B
     *
     * @return 返回 UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * 使用 BCrypt 算法对密码进行加密
     *
     * @param rawPassword 原始密码
     * @return 返回 BCrypt 加密的密码 (带前缀 {bcrypt})
     */
    public static String passwordByBCrypt(String rawPassword) {
        return "{bcrypt}" + bCryptPasswordEncoder.encode(rawPassword);
    }

    /**
     * 使用 BCrypt 算法判断密码是否有效
     *
     * @param rawPassword       原始密码
     * @param encryptedPassword 加密后的密码
     * @return 密码匹配返回 true，否则返回 false
     */
    public static boolean isPasswordValidByBCrypt(String rawPassword, String encryptedPassword) {
        encryptedPassword = encryptedPassword.replace("{bcrypt}", ""); // 去除前缀
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }

    /**
     * 今天日期的字符串表示，格式为 yyyy-MM-dd
     *
     * @return 返回格式为 yyyy-MM-dd 日期字符串
     */
    public static String today() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    }

    /**
     * 输出对象到控制台
     *
     * @param object 要输出的对象
     */
    public static void dump(Object object) {
        System.out.println(JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss.SSS", SerializerFeature.PrettyFormat));
    }

    /**
     * 使用PrettyTime对时间进行转换，比如返回：10分钟前、1小时前等
     *
     * @param date 要转换的时间
     * @return 转换过后的时间
     */
    public static String prettyTime(Date date) {
        return prettyTime.format(date).replace(" ", "");
    }

    /**
     * 获取指定日期月份第一天
     */
    public static Date firstDayOfMonth(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    /**
     * 获取指定日期月份最后一天
     */
    public static Date lastDayOfMonth(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        return c.getTime();
    }

    /**
     * 抓取 HTML 中图片 URL
     *
     * @param html
     * @return html 中所有图片的 URL 列表
     */
    public static List<String> imageUrlsFromHtml(String html) {
        List<String> imgUrls = new ArrayList<String>();
        if (StringUtils.isBlank(html)) {
            return imgUrls;
        }
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("img");
        if (elements == null || elements.size() == 0) {
            return imgUrls;
        }
        for (Element element : elements) {
            String url = element.attr("src");
            imgUrls.add(url);
        }

        return imgUrls;
    }

    /**
     * 获取题干中除去标签和空格超链接以外的内容
     */
    public static String getTextFromHtml(String html) {
        if (StringUtils.isBlank(html)) {
            return null;
        }
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("a");
        for (Element element : elements) {
            element.remove();
        }
        html = doc.body().text();
        return html;
    }

    /**
     * 解析学生回复json对象
     **/
    public static void analysisReply(String content) {
        JSONObject jsonObject = JSON.parseObject(content);
    }

    /**
     * 如果 id 不为 null 且大于 0 则是有效的 ID (数据库里的 ID 都是从 1 开始)
     *
     * @param id 进行有效性检查的 ID
     * @return id 大于 0 时返回 true，否则返回 false
     */
    public static boolean isValidId(Long id) {
        return id != null && id > 0;
    }

    /**
     * 如果 id 为 null 或者小于等于 0 则无效
     *
     * @param id 进行有效性检查的 ID
     * @return id 为 null 或者小于等于 0 返回 true，否则返回 false
     */
    public static boolean isInvalidId(Long id) {
        return !isValidId(id);
    }


    /**
     * 获取图片的大小
     * <p>
     * This solution is very quick as only image size is read from the file and not the whole image.
     * From: https://stackoverflow.com/questions/672916/how-to-get-image-height-and-width-using-java
     * <p>
     * Blows ImageIO.read() completely out of the water, both in terms of CPU time and memory usage.
     *
     * @param path 图片文件的路径
     * @return 返回图片大小的 dimension 对象
     */
    public static Dimension getImageSize(String path) {
        Dimension result = null;
        String suffix = FilenameUtils.getExtension(path);
        Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);

        if (iter.hasNext()) {
            ImageReader reader = iter.next();

            try (ImageInputStream stream = new FileImageInputStream(new File(path))) {
                reader.setInput(stream);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                result = new Dimension(width, height);
            } catch (IOException e) {
                logger.warn(e.getMessage());
            } finally {
                reader.dispose();
            }
        } else {
            logger.warn("No ImageReader found for given format: " + suffix);
        }

        return result;
    }

    /**
     * 使用从 classes 目录下相对路径为 path 的文件创建 InputStream
     *
     * @param path 文件相对 classes 目录的路径
     * @return 返回文件 path 对应的 InputStream
     */
    public static InputStream getStreamRelativeToClassesDirectory(String path) {
        return Utils.class.getClassLoader().getResourceAsStream(path);
    }

    /**
     * 把 List<T> 根据 key 进行分组为 Map<K, List<T>>，key 为类 T 的方法引用返回的值，例如用户名，ID 等。
     * 使用案例，把 users 根据用户名和 ID 进行分组，相同用户名的用户作为 map 的 value (List<User>)，并且限制每个 list 大小为 2:
     * List<User> users = new LinkedList<>();
     * Map<String, List<User>> usersMap1 = groupAndLimitMapListValueSize(users, 2, User::getUsername);
     * Map<Long,   List<User>> usersMap2 = groupAndLimitMapListValueSize(users, 2, User::getId);
     *
     * @param list       要进行分组的 list
     * @param size       每组元素的个数
     * @param classifier 分组的 key 的方法引用
     * @param <K>        map 的 key 的类型
     * @param <T>        map 的 value 的类型
     * @return 返回分组后的 map
     */
    public static <K, T> Map<K, List<T>> groupAndLimitMapListValueSize(List<T> list, int size, Function<? super T, ? extends K> classifier) {
        Map<K, List<T>> map = list.stream().collect(Collectors.groupingBy(classifier));

        map.forEach((key, valueList) -> {
            if (valueList.size() > size) {
                map.put(key, valueList.subList(0, size));
            }
        });

        return map;
    }

    public static void main(String[] args) {
        String text = "如果要编码的字节数不能被3整除，最后会多出1个或2个字节.";
        String encrypt = base64(text);
        String encryptUrl = base64UrlSafe(text);
        System.out.println(encrypt);
        System.out.println(encryptUrl);
        System.out.println(unbase64(encrypt));
        System.out.println(unbase64UrlSafe(encryptUrl));

        // 测试密码
        System.out.println(passwordByBCrypt("admin"));
        System.out.println(isPasswordValidByBCrypt("password", "$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG"));
        System.out.println(isPasswordValidByBCrypt("password", "{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG"));
    }

    /*
     * 获取js渲染过后的html页面
     * */
/*    public static String getAjaxPage(String url) throws Exception{
        WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
        webClient.setJavaScriptEnabled(true);
        webClient.setCssEnabled(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.setTimeout(Integer.MAX_VALUE);
        webClient.setThrowExceptionOnScriptError(false);
        HtmlPage rootPage = webClient.getPage(url);
        Thread.sleep(5000);
        return rootPage.asXml();
    }*/
    public static Document getDocument(String url) throws IOException, InterruptedException {
        WebClient wc = new WebClient(BrowserVersion.CHROME);
        //是否使用不安全的SSL
        wc.getOptions().setUseInsecureSSL(true);
        //启用JS解释器，默认为true
        wc.getOptions().setJavaScriptEnabled(true);
        //禁用CSS
        wc.getOptions().setCssEnabled(false);
        //js运行错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnScriptError(false);
        //状态码错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        //是否允许使用ActiveX
        wc.getOptions().setActiveXNative(false);
        //等待js时间
        wc.waitForBackgroundJavaScript(600 * 1000);
        //设置Ajax异步处理控制器即启用Ajax支持
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        //设置超时时间
        wc.getOptions().setTimeout(1000000);
        //不跟踪抓取
        wc.getOptions().setDoNotTrackEnabled(false);
        WebRequest request = new WebRequest(new URL(url));
        request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
        request.setAdditionalHeader("Cookie", "PLAY_LANG=cn; _plh=b9289d0a863a8fc9c79fb938f15372f7731d13fb; PLATFORM_SESSION=39034d07000717c664134556ad39869771aabc04-_ldi=520275&_lsh=8cf91cdbcbbb255adff5cba6061f561b642f5157&csrfToken=209f20c8473bc0518413c226f898ff79cd69c3ff-1539926671235-b853a6a63c77dd8fcc364a58&_lpt=%2Fcn%2Fvehicle_sales%2Fsearch&_lsi=1646321; _ga=GA1.2.2146952143.1539926675; _gid=GA1.2.1032787565.1539926675; _plh_notime=8cf91cdbcbbb255adff5cba6061f561b642f5157");
        try {
            //模拟浏览器打开一个目标网址
            HtmlPage htmlPage = wc.getPage(request);
            //为了获取js执行的数据 线程开始沉睡等待
            Thread.sleep(1000);//这个线程的等待 因为js加载需要时间的
            //以xml形式获取响应文本
            String xml = htmlPage.asXml();
            //并转为Document对象return
            return Jsoup.parse(xml);
            //System.out.println(xml.contains("结果.xls"));//false
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Unicode转中文
     * */
    public static String toUnicode(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) <= 256) {
                sb.append("\\u00");
            } else {
                sb.append("\\u");
            }
            sb.append(Integer.toHexString(s.charAt(i)));
        }
        return sb.toString();
    }

    public static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
        String[] strs = unicode.split("\\\\u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }

    /*
     * 读取json文件
     * */
    public static void dealJsonFile(String url) throws Exception {
        File file = new File("C:\\Users\\47477\\Desktop\\lwyCodeAdress\\FileUpload\\src\\main\\resources\\json\\hero.json");
        String bookJson = FileUtils.readFileToString(file, "UTF-8");
    }

    /**
     * @param url
     * @param dir
     * @param fileName
     */
    public static void downloadHttpUrl(String url, String dir, String fileName) {
        try {
            URL httpUrl = new URL(url);
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            FileUtils.copyURLToFile(httpUrl, new File(dir + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 到处excel表格
    * */
    public static void genExcelData(List<PaChong.Prize> prizes) throws  Exception{
        // 先生成一个excel文件
        String uuid = UUID.randomUUID().toString().substring(0,10).replace("-","");
        File file = new File ("C:/Users/47477/Desktop/"+uuid+"_prize.xlsx");
        if (!file.exists()) {
            file.createNewFile();
        }
        // 创建excel表
        Workbook wk = new HSSFWorkbook();
        // 创建sheet
        Sheet sheet = wk.createSheet("抽奖记录");
        // 当前excel创建第一行作为表头
        Row row = sheet.createRow(0);
        // 插入表头字段
        row.createCell(0).setCellValue("客户名称");
        row.createCell(2).setCellValue("奖品号码");
        row.createCell(1).setCellValue("奖品名称");
        // 将奖品数据插入excel表
        if (prizes.size() > 0) {
            for(int i =1; i<=prizes.size(); i++) {
                Row rowChild =  sheet.createRow(i);
                rowChild.createCell(0).setCellValue(prizes.get(i-1).getWinner());//客户名称
                sheet.setColumnWidth( 0,prizes.get(i-1).getWinner().getBytes().length*2*256);
                rowChild.createCell(2).setCellValue(prizes.get(i-1).getPrizeNum());//奖品号码
                sheet.setColumnWidth( 1,prizes.get(i-1).getPrizeName().getBytes().length*2*256);
                rowChild.createCell(1).setCellValue(prizes.get(i-1).getPrizeName());//奖品名称
            }
        }
        // 将文件写入到io流中
        OutputStream os =  new FileOutputStream(file);
        try {
            wk.write(os);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                wk.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
        public static String encodeStr(String str) {
            try {
                return new String(str.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String context) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "我的小秘书", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "sb", "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject("主题", "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(context, "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

    /*
    * 计算excel有效行数
    * */
    public static int getCellsCount() {
        Workbook wb = null;
        try {
            wb = new HSSFWorkbook(new FileInputStream("C:\\Users\\47477\\Desktop\\线下成绩导入模版.xlsx"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取第一个画布
        Sheet sheet = wb.getSheetAt(0);
        CellReference cellReference = new CellReference("A");
        boolean flag = false;
        for (int i = cellReference.getRow(); i <= sheet.getLastRowNum();) {
            Row r = sheet.getRow(i);
            if(r == null){
                //如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动
                sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);
                continue;
            }
            flag = false;
            for(Cell c : r){
                if(c.getCellType() != Cell.CELL_TYPE_BLANK){
                    flag = true;
                    break;
                }
            }
            if(flag){
                i++;
                continue;
            }else{//如果是空白行（即可能没有数据，但是有一定格式）
                if(i == sheet.getLastRowNum()){
                    //如果到了最后一行，直接将那一行remove掉
                    sheet.removeRow(r);
                }else{
                    //如果还没到最后一行，则数据往上移一行
                    sheet.shiftRows(i+1, sheet.getLastRowNum(),-1);
                }
            }
        }
        return sheet.getLastRowNum()+1;
    }
}

