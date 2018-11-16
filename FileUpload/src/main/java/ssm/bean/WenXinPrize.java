package ssm.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
public class WenXinPrize {
    private Long id; // 主键
    private String  prizeName; // 奖品名称
    private BigDecimal prizePrice; // 奖品价格
}
