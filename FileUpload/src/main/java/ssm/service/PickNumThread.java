package ssm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.bean.UserPrizeNum;
import ssm.mapper.DemoMapper;

import java.util.List;

@Service
public class PickNumThread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(PickNumThread.class.getName());

    @Autowired
    private DemoMapper demoMapper;

    private Object key = "key"; // 线程锁

    private static final int MIN_NUM = 20000; // 每个数字对应的号码从20000开始小于30000
    private static final int MAX_NUM = 30000; // 每个数字对应的号码从20000开始小于30000
    private static Long prizeId;
    private static String pickNum;

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    /*
     * 抽取最新号码逻辑
     * */
    @Override
    public void run() {
        System.out.println("进入run方法");
        getPickNum();
    }

    public String getPickNum() {
        synchronized (key) {
            logger.info("开始生成用户抽奖码,奖品id为----》" + prizeId);
            // 查询最新的一个号码
            List<UserPrizeNum> newUserPrizeNums = demoMapper.findNewNum(prizeId);
            if (newUserPrizeNums.size() > 0) {
                // 获取第一条数据为最新数据
                String newPickNum = newUserPrizeNums.get(0).getPrizeNum();
                int number = Integer.parseInt(newPickNum.substring(1, newPickNum.length()));
                char[] chars = newPickNum.toCharArray();
                char headChar = chars[0];
                if (number > MAX_NUM) {
                    // 字母加一
                    headChar = (char) (headChar + 1);
                } else {
                    number++;
                }
                pickNum = headChar + "" + number;
            } else {
                // 还没有人抽过
                pickNum = "A" + MIN_NUM;
            }
            // 睡一秒以免后面生成重复时间
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                logger.error("线程错误");
            }
        }
        return pickNum;
    }
}
