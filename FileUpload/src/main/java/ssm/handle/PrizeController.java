package ssm.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.bean.Result;
import ssm.bean.ResultConstant;
import ssm.bean.WenXinPrize;
import ssm.service.PrizeService;

import java.util.List;

/*
* 需求:
* 1.网页端: 奖品列表、添加奖品、删除奖品、开启抽奖、查看中奖名单、查看奖品详情
* 2.小程序: 获取网页端发布的奖品、获取抽奖码、查看中奖信息
* */
@Controller
public class PrizeController {
    private static Logger logger = LoggerFactory.getLogger(PrizeController.class.getName());

    @Autowired
    private PrizeService prizeService;

    @RequestMapping(value = Urls.PRIZES_LIST, method = RequestMethod.GET)
    @ResponseBody
    public List<WenXinPrize> findPrizes(@RequestParam (defaultValue = "0") int enable) {
        return prizeService.findPrizes(enable,null);
    }

    @RequestMapping(value = Urls.ADD_PRIZE, method = RequestMethod.POST)
    @ResponseBody
    public Result addPrize(@RequestBody WenXinPrize  wenXinPrize) {
        logger.info("添加奖品名称为---》"+wenXinPrize.getPrizeName());
        prizeService.addPrize(wenXinPrize);
        return new Result (ResultConstant.SUCCESS.getCode(),ResultConstant.SUCCESS.getDesc());
    }
}
