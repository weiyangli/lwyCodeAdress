package ssm.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
public class UserPrizeNum extends Result{
    private Long   id;       // 主键Id
    private Long  prizeId;   // 奖品ID
    private String openId;   // 用户微信openId唯一标识
    private String prizeNum; // 生成的奖品号码
    private String userName; // 用户微信名字
    private Date createTime; // 创建时间

    public static void main(String[] args) {
        UserPrizeNum userPrizeNum = new UserPrizeNum();
        userPrizeNum.initMe(ResultConstant.SUCCESS);
        //userPrizeNum.initMe(1,"成功");
        System.out.println(userPrizeNum.getCode()+"--->"+userPrizeNum.getDesc());
    }
}
