package ssm.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Skin {
    private Long id;
    private String skinName;
    private String skinImg;
    private String skinLevel;
    private String skinPrice;
}
