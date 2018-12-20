package ssm.mapper;

import org.apache.ibatis.annotations.Param;
import ssm.bean.HitPickNum;
import ssm.bean.UserPrizeNum;
import ssm.bean.WenXinPrize;

import java.util.Date;
import java.util.List;

public interface PrizeMapper {
    List<WenXinPrize> findPrizes(@Param("enable") int enable,@Param("pickTime") Date pickTime);

    void addPrize(WenXinPrize wenXinPrize);

    void addHitPicknum(HitPickNum hitPickNum);

    void updateEnable(Long prizeId);

    List<UserPrizeNum> findPickNum(@Param("prizeId")Long prizeId, @Param("pickTime")Date pickTime);
}
