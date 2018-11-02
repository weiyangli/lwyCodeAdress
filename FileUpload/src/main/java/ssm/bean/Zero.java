package ssm.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.expression.spel.ast.NullLiteral;

import java.util.List;

@Getter
@Setter
@Document(indexName = "zero", type = "zero")
@Accessors(chain = true)
public class Zero {
    private String id; // 主键
    private String key; // 主键
    private String name; // 英文名
    private String title; // 中文名
    private String tags; // 定位
    private String image; // 图片
}
