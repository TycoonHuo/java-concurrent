package com.huoyun.study.concurrent.thread.threadlocal;

/**
 * ThreadLocal 每个线程独立的
 *
 * @author huoguangyao
 * @date 2019/10/2 9:03 下午
 */
public class TestThreadLocal {

    public static void main(String[] args) {
        ThreadLocal<Person> tl = new ThreadLocal<>();

        new Thread(() -> {
            tl.set(new Person("zhangsan"));
            Person person = tl.get();
            System.out.println("t1:->"+person);
        }).start();

        new Thread(()->{
            Person person = tl.get();
            System.out.println(person);
        }).start();

    }

    static class Person {
        private String name;

        Person(String name) {
            this.name = name;
        }

    }
}
