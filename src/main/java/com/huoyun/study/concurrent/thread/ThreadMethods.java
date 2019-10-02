package com.huoyun.study.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * 线程的 三个 方法
 * sleep
 * yield 用不到
 * join
 *
 * @author huoguangyao
 * @date 2019/9/28 6:44 下午
 */
public class ThreadMethods {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                if (50 == i) {
                    try {
                        TimeUnit.SECONDS.sleep(3L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(t1.getState());
                if(50==i) {
                    try {
                        t1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        });

        t1.start();
        t2.start();

    }
}
