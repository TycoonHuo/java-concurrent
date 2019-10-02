package com.huoyun.study.concurrent.thread.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java显示锁
 * 这个锁 好在哪里 多了一些能力。
 * 比如：
 * tryLock
 * 是否公平
 *
 * @author huoguangyao
 * @date 2019/10/2 1:56 下午
 */
public class TestReentryLock {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("想让我释放锁吗？ 再等等 我拉个屎");
                try {
                    TimeUnit.SECONDS.sleep(5L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 一定要把锁给释放了
                reentrantLock.unlock();
            }
        }).start();

        new Thread(()->{
            try {
                boolean b = reentrantLock.tryLock(5, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName()+":"+b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }).start();
    }
}
