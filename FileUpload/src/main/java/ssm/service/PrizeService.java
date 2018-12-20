package ssm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ssm.bean.WenXinPrize;
import ssm.mapper.PrizeMapper;

import java.util.Date;
import java.util.List;

@Service
public class PrizeService {
    private static Logger logger = LoggerFactory.getLogger(PrizeService.class.getName());
    @Autowired
    private PrizeMapper prizeMapper;

    @Autowired
    private IdWorker idWorker;

    public List<WenXinPrize> findPrizes(int enable, @Nullable Date pickTime) {
        return prizeMapper.findPrizes(enable, pickTime);
    }

    public void addPrize(WenXinPrize wenXinPrize) {
        if (wenXinPrize.getId() == 0) {
            wenXinPrize.setId(idWorker.nextId());
        }
        prizeMapper.addPrize(wenXinPrize);
    }
}
