package ssm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.bean.WenXinPrize;
import ssm.handle.PrizeController;
import ssm.mapper.PrizeMapper;

import java.util.List;

@Service
public class PrizeService {
    private static Logger logger = LoggerFactory.getLogger(PrizeService.class.getName());
    @Autowired
    private PrizeMapper prizeMapper;

    @Autowired
    private IdWorker idWorker;

    public List<WenXinPrize> findPrizes() {
        return prizeMapper.findPrizes();
    }

    public void addPrize(WenXinPrize wenXinPrize) {
        wenXinPrize.setId(idWorker.nextId());
        prizeMapper.addPrize(wenXinPrize);
    }
}
