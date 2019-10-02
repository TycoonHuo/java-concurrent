package com.huoyun.study.concurrent.thread;

/**
 * 线程安全的单例模式
 * 我理解的单例模式就是:
 * 构造方法私有，提供静态方法给出单例对象
 *
 * <pre>private final static Singleton INSTANCE = new Singleton();</pre>
 *
 * 饿汉式， 这个单例由JVM保证 因为是static的。
 * 这也是工作中用的最多的单例模式。
 * 有些人就出来杠了， 我还没到用的时候呢，你就初始化对象了， 这样太消耗性能了。 巴拉巴拉
 * <p>
 * 下面代码 来线程安全的 单例模式
 *
 * @author huoguangyao
 * @date 2019/9/29 12:47 上午
 *
 */
public class Singleton {
    /**
     * 开始没有初始化
     */
    private static Singleton instance = null;

    /**
     * 私有构造 进制外部new对象
     */
    private Singleton() {
    }

    /**
     * 这有很多线程一起来
     * @return 单例对象
     */
    public static Singleton getInstance() {
        // 如果还没有对象 这步不加锁 是为了性能。
        if (instance == null) {
            // 这有多个线程在这门口站着了 他们只记得 instance是null 所以有下面的double check
            synchronized (Singleton.class) {
                // double check
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000000; i++) {
            new Thread(() -> {
                System.out.println(Singleton.getInstance());
            }).start();
        }
    }
}
