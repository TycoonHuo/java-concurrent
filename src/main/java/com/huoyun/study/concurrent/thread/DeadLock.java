package com.huoyun.study.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * 玩玩 死锁
 * 俩人互相不松手
 * @author huoguangyao
 * @date 2019/9/28 6:23 下午
 */
public class DeadLock {
    Object o1 = new Object();
    Object o2 = new Object();

    public static void main(String[] args) throws Exception{
        DeadLock deadLock = new DeadLock();
        Thread t1 = new Thread(() -> {
            deadLock.go();
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            deadLock.back();
        });
        t2.start();

        TimeUnit.SECONDS.sleep(10L);
        // 这个能看出来 没有拿到锁的线程的状态 是 blocked
        System.out.println(t1.getState());
        System.out.println(t2.getState());
    }

    void go() {
        synchronized (o1){
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":我要出门");
            synchronized (o2){
                System.out.println(Thread.currentThread().getName()+":我要开门");
            }
        }
    }

    void back() {
        synchronized (o2){
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":我要回来");
            synchronized (o1){
                System.out.println(Thread.currentThread().getName()+":我要开门");
            }
        }
    }



}
