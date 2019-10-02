package com.huoyun.study.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程之间的可见性
 *
 * @author huoguangyao
 * @date 2019/9/29 12:28 上午
 */
public class TheVolatile {
    volatile private boolean flag = true;

    public static void main(String[] args) {
        TheVolatile obj = new TheVolatile();
        new Thread(() -> {
            System.out.println("start");
            for (; ; ) {
//                System.out.println("run");
                if (!obj.flag) {
                    break;
                }
            }

            System.out.println("end");
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3L);
            System.out.println("修改前:" + obj.flag);
            obj.flag = false;
            System.out.println("修改后:" + obj.flag);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
