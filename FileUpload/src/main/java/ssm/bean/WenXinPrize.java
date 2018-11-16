package ssm.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class WenXinPrize {
    private Long id; // 主键
    private String  prizeName; // 奖品名称
    private BigDecimal prizePrice; // 奖品价格
    private String prizeImg;  // 奖品图片
    private int isEnable;     // 是否启动抽奖  // 0:未启动抽奖 1:已启动抽奖 2:抽奖结束
    private int bePickNum;    // 被设置为奖品次数
    private Date pickTime;    // 抽奖时间
    private Date createTime;  // 创建时间
}
