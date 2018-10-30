package ssm.handle;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
@Getter
@Setter
public class PaChong implements Runnable{
    private String name;

    private Object key = "key";

    public PaChong(String name) {
        this.name = name;
    }    /*
     * // 线程锁和分布式锁的区别:
     * 如果系统在单机服务器上运行时，直接通过线程锁就可以解决，但是如果线程过多会占用过多的cpu，
     * 对服务器的运行有影响，可以添加线程锁中的互斥锁进行控制，如果有线程占用当前接口，其他线程挂起并不占用cpu，
     * 等待上一个线程结束后，等待的线程会争抢当前线程，抢到的使用(就像一个厕所大家等着上一样)。
     * 分布式锁:它和线程锁的使用场景不同，分布式锁是系统部署在多机系统，但是调用的是一台数据库的数据，都可以操作数据库数据，
     * 这时候就有问题啦，你在操作的时候改变了数据，但是我去拿数据的时候，还是原来的数据，而不是你处理过后的数据，
     * 这个时候就可以用分布式锁啦，可以通过redis存储一个锁的键值，每个服务器的线程都去访问这个键值，当返回未true代表有人在使用，
     * 当返回为false时代表使用结束，其他服务器的线程可以用了。
     * */
    public static void main(String[] args) throws  Exception {
        // 点击按钮随机转动获取对应奖品
        PaChong paChong = new PaChong("张三");
        new Thread(paChong).start();
        PaChong paChong2 = new PaChong("李四");
        new Thread(paChong2).start();
        PaChong paChong3 = new PaChong("王二");
        new Thread(paChong3).start();
        PaChong paChong4 = new PaChong("码子");
        new Thread(paChong4).start();
    }
    // 随机获取对应的礼品
    public void random () {
        // 计算当前用户属于什么级别的用户(级别高的可以获取价值高的奖品)
        synchronized (key){
            Random r = new Random();
            int num = r.nextInt(10000) % (10000) + 1;
            System.out.println(name+"朋友抽到的号码是"+num);
            Map<Integer,String> prizesMap = getPrizes();
            // 奖品池分为
            String prize = prizesMap.get(num);
            try {
                Thread.sleep(5000);
            }catch (Exception e) {
                System.out.println("线程终端");
            }

            if (prize.equals("谢谢参与")) {
                System.out.println(name+"这次运气不是太好哦，再来一次吧");
            } else {
                System.out.println("恭喜"+name+"朋友获得了"+prize);
            }
        }
    }

    @Override
    public void run(){
        System.out.println(name+"经来咯");
        random();
    }

    @Getter
    @Setter
    public class Prize {
        private String prizeName;
        private double prizeRate;

        /* 构造方法 */
        public Prize(String prizeName,double prizeRate) {
            this.prizeName = prizeName;
            this.prizeRate = prizeRate;
        }
    }

    // 创建一个奖品池
    public Map<Integer,String> getPrizes() {
        Map<Integer,Prize> prizesMap = new HashMap<>();
        prizesMap.put(1,new Prize("Iphone X 64G",0.5));
        prizesMap.put(2,new Prize("pad mini",2.0));
        prizesMap.put(3,new Prize("Aj 南海岸",3.0));
        prizesMap.put(4,new Prize("Aj6 手稿",3.0));
        prizesMap.put(5,new Prize("Aj1 黑脚趾",1.5));
        prizesMap.put(6,new Prize("小米耳机",20.0));
        prizesMap.put(7,new Prize("谢谢参与",70.0));
        // 按照中奖概率创建一万个奖品
        Map<Integer,String> prizes = new HashMap<>();
        Set<Integer> keys = prizesMap.keySet();
        int num = 0; // 记录是第几个奖品
        for (Integer key : keys) {
            // 填充当前奖品库
            int count = (int)(prizesMap.get(key).getPrizeRate() / 100 * 10000) + num;
            int cs = num;
            for (int i = num + 1; i <= count; i++) {
                prizes.put(i, prizesMap.get(key).getPrizeName());
                cs++;
            }
            num = cs;
        }
        return prizes;
    }
}