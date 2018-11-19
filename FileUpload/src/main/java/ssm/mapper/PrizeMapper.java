package ssm.mapper;

import ssm.bean.WenXinPrize;

import java.util.List;

public interface PrizeMapper {
    List<WenXinPrize> findPrizes();

    void addPrize(WenXinPrize wenXinPrize);
}
