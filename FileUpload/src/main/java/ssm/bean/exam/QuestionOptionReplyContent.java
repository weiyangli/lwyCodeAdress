package ssm.bean.exam;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 作业、考试问题的回答，只用于提交问题选项作答信息。把该对象转为json格式进行持久化，作为问题选项的作答内容。
 */
@Getter
@Setter
@Accessors(chain = true)
public class QuestionOptionReplyContent {

    private Long optionId;
    private String content;
    private List<Audio> audios;  // 声音
    private List<String> images;  // 图片

    @Getter
    @Setter
    public  static class Audio {
        private String url;   // 路径
        private int duration; // 声音时长
    }
}
