package ssm.mq;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.jms.core.JmsTemplate;
import ssm.bean.MqMessage;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * ActiveMQ 消息生产者
 */
@Getter
@Setter
@Accessors(chain = true)
public class MessageProducer {
    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "pdfConvertQueue")
    private Destination pdfConvertQueue; // 文档转换队列

    @Resource(name = "mp4ConvertQueue")
    private Destination mp4ConvertQueue; // 视频转换队列

    @Resource(name="zeroPickPrize")
    private Destination zeroPickPrize;

    /**
     * 发送转换文档为 PDF 的消息
     *
     * @param path 要转换的文件的路径
     */
    public void sendConvertPdfMessage(final String path) {
        jmsTemplate.send(pdfConvertQueue, session -> {
            // 创建 JSON 格式的消息对象
            // 1. 设置消息的类型
            // 2. 设置消息的内容
            MqMessage message = new MqMessage();
            message.setType(1);
            message.getContent().put("path", path);

            return session.createTextMessage(JSON.toJSONString(message));
        });
    }

    /**
     * 发送转换视频为 MP4 的消息
     *
     * @param path 要转换的文件的路径
     */
    public void sendConvertMp4Message(final String path) {
        jmsTemplate.send(mp4ConvertQueue, session -> {
            // 创建 JSON 格式的消息对象
            // 1. 设置消息的类型
            // 2. 设置消息的内容
            MqMessage message = new MqMessage();
            message.setType(1);
            message.getContent().put("path", path);

            return session.createTextMessage(JSON.toJSONString(message));
        });
    }

    /**
     * 英雄抽取礼物
     *
     * @param name 英雄名
     */
    public void pickPrize(final String name) {
        jmsTemplate.send(zeroPickPrize, session -> {
            // 创建 JSON 格式的消息对象
            // 1. 设置消息的类型
            // 2. 设置消息的内容
            MqMessage message = new MqMessage();
            message.setType(2);
            message.getContent().put("zeroName", name);

            return session.createTextMessage(JSON.toJSONString(message));
        });
    }
}
