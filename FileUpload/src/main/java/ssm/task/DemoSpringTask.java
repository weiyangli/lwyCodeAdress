package ssm.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import ssm.bean.HitPickNum;
import ssm.bean.UserPrizeNum;
import ssm.bean.WenXinPrize;
import ssm.mapper.PrizeMapper;
import ssm.service.IdWorker;
import ssm.service.PrizeService;
import ssm.service.RedisService;
import ssm.util.Utils;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class DemoSpringTask {

    private static Logger logger = LoggerFactory.getLogger(DemoSpringTask.class.getName());
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private PrizeService prizeService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private PrizeMapper prizeMapper;

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    @Value("${myEmailAccount}")
    private String myEmailAccount;

    @Value("${myEmailPassword}")
    private String myEmailPassword;

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易126邮箱的 SMTP 服务器地址为: smtp.126.com
    public static String myEmailSMTPHost = "smtp.163.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "474777260@qq.com";

    public void testDemo() throws Exception {
        Long taskId = idWorker.nextId();
        // 定时发送邮件提醒
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     取消下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd E");
        Date time = new Date();
        String data = simpleDateFormat.format(time);
        logger.info("今天是"+data);
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);

        // 3. 创建一封邮件
        MimeMessage message = Utils.createMimeMessage(session, myEmailAccount, receiveMailAccount,"今天是"+data+"haveFun");

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //
        //    PS_01: 如果连接服务器失败, 都会在控制台输出相应失败原因的log。
        //    仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接,
        //    根据给出的错误类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        transport.connect(myEmailAccount, myEmailPassword);

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

        // 7. 关闭连接
        transport.close();
    }

    /*
     * 定时抽取奖品
     * */
    public void startPickPrize() {
        // 根据当前时间查询该时段的奖品
        // 查询有关奖品的抽奖码
        // 从所有的参与号码中抽取中奖者(该处可增加复杂逻辑:例如在该店消费越高的抽奖几率越高之类的，或者用户的星级)

        List<WenXinPrize> wenXinPrizeList = prizeService.findPrizes(1, new Date());
        if (wenXinPrizeList.size() > 0) {
            logger.info("有待开奖的活动---》"+wenXinPrizeList.size());
            wenXinPrizeList.forEach(wenXinPrize -> {
                List<UserPrizeNum> userPrizeNums = prizeMapper.findPickNum(wenXinPrize.getId(),wenXinPrize.getPickTime());
                if (userPrizeNums.size() > 0) {
                    logger.info(com.alibaba.fastjson.JSON.toJSONString(userPrizeNums));
                    Collections.shuffle(userPrizeNums);
                    userPrizeNums.subList(0, 1);
                    HitPickNum hitPickNum = new HitPickNum();
                    hitPickNum.setId(idWorker.nextId());
                    hitPickNum.setPrizeId(wenXinPrize.getId());
                    hitPickNum.setPrizeNum(userPrizeNums.get(0).getPrizeNum());
                    // 保存中奖者信息
                    prizeMapper.addHitPicknum(hitPickNum);
                }
                // 将奖品修改为未设置抽奖礼品
                prizeMapper.updateEnable(wenXinPrize.getId());
            });
        }
    }
}
