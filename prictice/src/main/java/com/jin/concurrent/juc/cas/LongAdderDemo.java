package com.jin.concurrent.juc.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderDemo {

    static LongAdder longAdder = new LongAdder();


    static void testAtomicLongVSLongAdder(int threadCnt, int times) throws InterruptedException {
        long start1 = System.currentTimeMillis();
        testAtomicLong(threadCnt, times);
        long end1 = System.currentTimeMillis();

        long start2 = System.currentTimeMillis();
        testLongAdder(threadCnt, times);
        long end2 = System.currentTimeMillis();

        System.out.println("AtomicLong: "+(end1 - start1) + "  longAdd: " + (end2 - start2));

        System.out.println("");
    }



    static void testAtomicLong(int threadCnt, int times) throws InterruptedException {

        AtomicLong atomicLong = new AtomicLong();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadCnt; i++) {
            list.add(new Thread(()->{

                for (int j = 0; j < times; j++) {
                    atomicLong.getAndIncrement();
                }
            }));
        }

        for (Thread thread : list) {
            thread.start();
        }

        for (Thread thread : list) {
            thread.join();
        }
    }

    static void testLongAdder(int threadCnt, int times) throws InterruptedException {
        LongAdder longAdder = new LongAdder();

        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < threadCnt; i++) {
            list.add(new Thread(()->{
                for (int j = 0; j < times; j++) {
                    longAdder.increment();
                }
            }));
        }

        for (Thread thread : list) {
            thread.start();
        }
        for (Thread thread : list) {
            thread.join();
        }
    }



    public static void main(String[] args) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        longAdder.add(100);
        testAtomicLongVSLongAdder(1, 1000000);
        testAtomicLongVSLongAdder(10, 1000000);
        testAtomicLongVSLongAdder(20, 1000000);
        testAtomicLongVSLongAdder(30, 1000000);
        testAtomicLongVSLongAdder(80, 1000000);

    }


}
