package ssm.mq;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ssm.bean.MqMessage;
import ssm.handle.PaChong;
import ssm.service.PrizesThread;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 视频转换为 MP4 的消息消费者
 */
public class ZeroPickPrizeConsumer implements MessageListener {
    private static Logger logger = LoggerFactory.getLogger(ZeroPickPrizeConsumer.class);


    public void onMessage(Message message) {
        // 这里我们知道生产者发送的就是一个纯文本消息，所以这里可以直接进行强制转换
        TextMessage textMsg = (TextMessage) message;

        try {
            MqMessage msg = JSON.parseObject(textMsg.getText(), MqMessage.class);
            logger.debug("抽取礼物：" + JSON.toJSONString(msg));
            String path = (String) msg.getContent().get("zeroName");
            PaChong paChong = new PaChong(path);
            PrizesThread prizesThread = new PrizesThread(paChong);
            new Thread(prizesThread).start();
        } catch (JMSException e) {
            logger.warn(ExceptionUtils.getStackTrace(e));
        }
    }
}
