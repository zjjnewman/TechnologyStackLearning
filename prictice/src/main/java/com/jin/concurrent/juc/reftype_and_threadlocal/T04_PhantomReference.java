package com.jin.concurrent.juc.reftype_and_threadlocal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * PhantomReference 虚引用，管理直接内存，在新版的JVM支持直接内存引用，
 *      在io过程中，jvm不必从 外部内存 copy数据到自己实例的内存，而是在自己内存内设一个 指针对象，可以指向 外部内存 的某个空间，直接读取
 *      此空间的数据，直接内存访问需要的IO数据
 * 回收机制 分为两步：
 *      1. M()对象在被回收时，PhantomReference对象被放在 传进去的队列里（钩子函数跟踪？）。（假设 M()对象指向 直接内存）
 *      2. 通过队列进行回收（队列中存放的是 虚引用对象），JVM会对 虚引用对象指向的对象 特殊处理（因为他们指向直接内存），
 *      3. 实质上虚引用对象只是起到一个通知的作用，方便我们接到通知 做出处理
 *      由下面程序可清楚的知道，虚引用对象PhantomReference不会被马上回收，而是放在队列里，等到被JVM扫描到，发出信号
 * nio netty 会用到 直接内存管理 Direct By Buffer
 */
public class T04_PhantomReference {

    private final static List<byte[]> LIST = new LinkedList<>();
    private final static ReferenceQueue<M> QUEUE = new ReferenceQueue<>();
    // 下面线程是否结束的标志，通知给线程2
    private static Boolean isInterrupt = false;

    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);

        System.out.println(phantomReference.get());

        // 使用线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);


        fixedThreadPool.execute(()->{
            while (true){
                try {
                    LIST.add(new byte[1024 * 1024]);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
//                System.out.println(phantomReference.get());
            }
        });

        fixedThreadPool.execute(()->{

            while (true){
                Reference<? extends M> poll = QUEUE.poll();
                if(poll != null){
                    System.out.println("-----虚引用对象被JVM回收了，----："+ poll);
                }

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(isInterrupt);
                if(isInterrupt){
                    Thread.currentThread().interrupt();
                    break;
                }

                fixedThreadPool.shutdownNow();
            }
        });
    }
}
