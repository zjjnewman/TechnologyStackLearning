package com.jin.concurrent.juc.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo03 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger a = new AtomicInteger(0);

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        while (!a.compareAndSet(a.get(), a.get() + 1)){}
                    }

                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println(a.get());
    }
}
