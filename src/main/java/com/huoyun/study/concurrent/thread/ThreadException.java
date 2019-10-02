package com.huoyun.study.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * 出现异常的时候锁会失效
 * 这个时候别的线程会闯进来
 *
 * @author huoguangyao
 * @date 2019/9/28 6:58 下午
 */
public class ThreadException {

    public static void main(String[] args) {
        ThreadException threadException = new ThreadException();
        new Thread(() -> {
            try {
                threadException.m();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                threadException.m();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    synchronized void m() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
            TimeUnit.SECONDS.sleep(1L);
            if(5 == i){
                throw new Exception("exception...");
            }
        }
    }

 }
