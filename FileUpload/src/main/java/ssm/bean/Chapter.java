package ssm.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
@Getter
@Setter
@Accessors(chain = true)
public class Chapter {
    private String code;  // 唯一code
    private String title; // 标题
    private int sequenceNum; // 排序
    private List<Chapter>  children; // 子节点
}
