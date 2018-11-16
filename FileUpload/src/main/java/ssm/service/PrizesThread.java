package ssm.service;

import org.springframework.stereotype.Service;
import ssm.handle.PaChong;

public class PrizesThread implements Runnable {
    private PaChong paChong ;

    public PrizesThread(PaChong paChong) {
        this.paChong = paChong;
    }
    @Override
    public void run() {
        System.out.println(paChong.getName()+"朋友参与了抽奖");
        paChong.random();
    }
}
