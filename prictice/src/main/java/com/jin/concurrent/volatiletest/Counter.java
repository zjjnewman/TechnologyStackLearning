package com.jin.concurrent.volatiletest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发之volatile底层原理
 * https://www.cnblogs.com/awkflf11/p/9218414.html
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    /**
     * Cas 线程安全计数器
     */
    private void safeCount(){
        for(;;){
            int i = atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if(suc){
                break;
            }
        }
    }


    /**
     * 非线程安全计数器
     */
    private void count(){
        i++;
    }


    public static void main(String[] args) throws InterruptedException {

        for(;;){
            final Counter cas = new Counter();
            List<Thread> ts = new ArrayList<>(600);

            long start = System.currentTimeMillis();
            for (int j = 0; j < 10; j++) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 100; i++) {
                            cas.count();
                            cas.safeCount();
                        }
                    }
                });
                ts.add(t);
            }

            for (Thread t : ts) {
                t.start();
            }

            for (Thread t : ts) {
                t.join();
            }

            if (cas.i != 1000){
                System.out.println("cas.i = " + cas.i);
                System.out.println("cas.atomicI.get() = " + cas.atomicI.get());
                System.out.println("用时 = " + (System.currentTimeMillis() -  start));
                break;
            } else {
                ts = null;
                cas.i = 0;
                cas.atomicI = new AtomicInteger(0);
                Runtime.getRuntime().gc();
            }
        }

    }
}
