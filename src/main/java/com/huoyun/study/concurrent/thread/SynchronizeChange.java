package com.huoyun.study.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * synchronize 锁的对象 发生了变化 锁失效
 *
 * @author huoguangyao
 * @date 2019/9/29 7:34 下午
 */
public class SynchronizeChange {
    private Object o = new Object();



    public static void main(String[] args) {
        SynchronizeChange synchronizeChange = new SynchronizeChange();
        new Thread(()->{
            synchronizeChange.m();
        }).start();
        new Thread(()->{
            synchronizeChange.m();
        }).start();
    }

    void m(){
        synchronized (o){
            System.out.println(Thread.currentThread().getName()+":" + "有锁才能执行");
//            this.o = "change"; 这里把锁的引用指向别的地方了。 锁就失效了
            try {
                TimeUnit.SECONDS.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
