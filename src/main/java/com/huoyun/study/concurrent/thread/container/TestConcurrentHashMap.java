package com.huoyun.study.concurrent.thread.container;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *
 * @author huoguangyao
 * @date 2019/10/2 10:24 下午
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<Long, Long> concurrentHashMap = new ConcurrentHashMap<>();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
//                concurrentHashMap.put()
            });
        }

    }
}
