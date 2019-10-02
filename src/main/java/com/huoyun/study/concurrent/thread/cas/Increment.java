package com.huoyun.study.concurrent.thread.cas;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * 并发情况下 对一个共享数据的累加 有三种方法
 * 1、加锁            悲观锁
 * 2、AtomicLong     CAS
 * 3、LongAdder      分段锁 线程越多 效果越明显
 * <p>
 * 对比这三种方式的效率
 *
 * @author huoguangyao
 * @date 2019/10/2 1:21 下午
 */
public class Increment {
    private static long num;
    private static AtomicLong atomicLong = new AtomicLong();
    private static LongAdder longAdder = new LongAdder();
    private static Object lock = new Object();

    private final static int LOOP_NUMS = 10000;

    public static void main(String[] args) {
        // 1000个线程
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < LOOP_NUMS; j++) {
                    synchronized (lock) {
                        num++;
                    }
                }
            });
        }
        performance(threads, "synchronize");

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < LOOP_NUMS; j++) {
                    atomicLong.incrementAndGet();
                }
            });
        }
        performance(threads, "atomic");

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < LOOP_NUMS; j++) {
                    longAdder.increment();
                }
            });
        }
        performance(threads, "LongAdder");

    }

    private static void performance(Thread[] threads, String method) {
        long start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(method + "::耗时：" + (end - start));
    }
}
