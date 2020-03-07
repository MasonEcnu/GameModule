package com.mason.game.red_packet;

import com.mason.game.utils.RandomUtil;

import java.math.BigDecimal;

/**
 * Created by mwu on 2020/1/2
 */
public class RedPacket {
    private volatile double money;
    private volatile int num;
    private volatile double totalMoney = 0.0;

    public RedPacket(int num, double money) {
        this.num = num;
        this.money = money;
    }

    public RedPacket(int num, int money) {
        this.num = num;
        this.money = money;
    }

    public synchronized void snatch() {
        if (num <= 0 || money <= 0) {
            double bigTotal = new BigDecimal(totalMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double bigMoney = new BigDecimal(money).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println(Thread.currentThread().getName() + "红包抢完了，总计:" + bigTotal + ", 剩余:" + bigMoney);
            return;
        }
        BigDecimal bd;
        double value;
        if (num == 1) {
            bd = new BigDecimal(money);
        } else {
            bd = new BigDecimal(RandomUtil.between(0.01, money / num * 2 - 0.01));
        }
        value = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(Thread.currentThread().getName() + "抢到了" + value + "元！");
        money -= value;
        num -= 1;
        totalMoney += value;
    }

    public void calcMethod() {
        // 1.二倍均值法
        // 每次抢到的金额 = 随机区间 [0.01，m /n × 2 - 0.01]元
        // 这个公式，保证了每次随机金额的平均值是相等的，不会因为抢红包的先后顺序而造成不公平
        RandomUtil.between(0.01, money / num * 2 - 0.01);

        // 2.线段切割法
        // 如何确定每一条子线段的长度呢？
        //由“切割点”来决定。当n个人一起抢红包时，就需要确定n-1个切割点。
        //因此，当n个人一起抢总金额为m的红包时，我们需要做n-1次随机运算，以此确
        //定n-1个切割点。随机的范围区间是[1， m-1]。
        //当所有切割点确定以后，子线段的长度也随之确定。此时红包的拆分金额，就
        //等同于每个子线段的长度。
        //这就是线段切割法的思路，在这里需要注意以下两点。
        //1. 当随机切割点出现重复时，如何处理。
        //2. 如何尽可能降低时间复杂度和空间复杂度。
    }
}
