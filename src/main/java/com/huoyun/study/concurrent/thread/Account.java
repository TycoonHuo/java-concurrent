package com.huoyun.study.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * 账户类 读方法 和写方法是否都需要加锁
 *
 * @author huoguangyao
 * @date 2019/9/28 5:55 下午
 */
public class Account {
    private Long money;

    /**
     * 这个方法百分之一万是需要上锁的
     */
    public synchronized void setMoney(Long money) {
        this.money = money;
        System.out.println("存好了");
    }

    /**
     * 读取需要10s
     * 读和取用到的是同一把锁。 所以必须等读完了才能开始存
     */
    public synchronized Long getMoney() {
        try {
            TimeUnit.SECONDS.sleep(2L);
            System.out.println("读完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return money;
    }

    public static void main(String[] args) {
        Account account = new Account();

        new Thread(() -> {
            account.getMoney();
        }).start();


        new Thread(() -> {
            account.setMoney(100000L);
        }).start();


    }
}
