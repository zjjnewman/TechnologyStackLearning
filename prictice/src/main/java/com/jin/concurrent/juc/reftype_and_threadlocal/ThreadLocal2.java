package com.jin.concurrent.juc.reftype_and_threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal：
 *      两个线程用的都是同一个 tl对象，按道理应该是可以拿到，里面的person对象，但是没拿到，说明ThreadLocal对象 对它存储的对象 线程隔离，
 *      即tl里存储的对象和 线程本身绑定，其他线程访问不到
 */
public class ThreadLocal2 {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

        fixedThreadPool.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(tl.get());
        });

        fixedThreadPool.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(new Person());
        });

        fixedThreadPool.shutdown();
    }


    static class Person{
        String name = "zhangsan";
    }
}
