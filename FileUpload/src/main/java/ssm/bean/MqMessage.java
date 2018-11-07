package ssm.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 消息队列的消息对象
 */
@Getter
@Setter
@Accessors(chain = true)
public class MqMessage {
    public static final int TYPE_FILE_CONVERT = 1; // 转换文件

    private int type; // 消息类型: 1 为转换文件 2 抽取礼物
    private JSONObject content = new JSONObject(); // 消息内容

    public MqMessage() {

    }

    public MqMessage(int type, JSONObject content) {
        this.type = type;
        this.content = content;
    }
}
