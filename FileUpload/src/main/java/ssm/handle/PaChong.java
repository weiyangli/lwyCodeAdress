package ssm.handle;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
/*
* 谈谈我对多线程的想法:
* 实现多线程两种方式 继承 thread 或者实现 runabled 类  重写 run 方法
* 多线程 = 一个接口同一时间被多次请求。这总情况会产生多个线程，如果多个线程之间执行的代码
* 使用的属性不存在互相影响情况，像单例模式一样一个值如果被线程反复使用，可能会使值发生变化，这个时候
* 会出现当前线程影响属性值，但是下一个线程拿到的值还是没有影响之前的值，这样计算下去业务肯定会有问题的，后面来的进程使用的
* 值，其实已经被使用过。这种情况使用线程同步来控制。即将影响该值的代码写在 synchronized 关键字中 可以用 sleep 来阻塞线程，
* 使其他线程稍作等待，sleep 时间过后线程再来争抢当前属性使用。还可以使用 wait 更加灵活等到对应的 notify 方法时释放对象锁。
* */
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
        PaChong paChong2 = new PaChong("历史");
        new Thread(paChong2).start();
        PaChong paChong3 = new PaChong("王二");
        new Thread(paChong3).start();
    }
    // 随机获取对应的礼品
    public void random () {
        // 计算当前用户属于什么级别的用户(级别高的可以获取价值高的奖品)
        synchronized (key){
            Random r = new Random();
            int num = r.nextInt(10000) % (10000) + 1;
            System.out.println(name+"朋友抽到的号码是"+num);
            // 创建奖品池
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
            System.out.println("-------------------------------------------->>");
       }
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
        prizesMap.put(2,new Prize("pad mini",0.5));
        prizesMap.put(3,new Prize("Aj 南海岸",2.0));
        prizesMap.put(4,new Prize("Aj6 手稿",1.0));
        prizesMap.put(5,new Prize("Aj1 黑脚趾",1.0));
        prizesMap.put(6,new Prize("小米耳机",5.0));
        prizesMap.put(7,new Prize("谢谢参与",90.0));
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
    @Override
    public void run() {
        System.out.println("fdsagasd");
        random();
    }
}