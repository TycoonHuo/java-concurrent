package com.huoyun.study.concurrent.thread;

/**
 * 创建线程的方法
 * 01 继承Thread类
 * 02 实现Runnable接口
 * 03 线程池(如果有03的话)
 * @author huoguangyao
 * @date 2019/9/28 6:32 下午
 */
public class CreateThread extends Thread {

    public static void main(String[] args) {

        // 继承Thread类 重写run方法
        /*new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }.start();

        new Runnable(){
            @Override
            public void run() {

            }
        };*/

        new Thread().start();

        for (; ; ) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
