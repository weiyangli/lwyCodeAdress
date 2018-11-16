package ssm.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
/*
* 需求:
* 1.网页端: 奖品列表、添加奖品、删除奖品、开启抽奖、查看中奖名单、查看奖品详情
* 2.小程序: 获取网页端发布的奖品、获取抽奖码、查看中奖信息
* */
@Controller
public class prizeController {
    private static Logger logger = LoggerFactory.getLogger(prizeController.class.getName());
}
