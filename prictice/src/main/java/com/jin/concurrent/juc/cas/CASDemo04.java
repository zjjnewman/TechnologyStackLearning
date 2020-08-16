package com.jin.concurrent.juc.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CASDemo04 {
    public static void main(String[] args) throws InterruptedException {
        AtomicStampedReference<Integer> a = new AtomicStampedReference(0, 0);

        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        while (!a.compareAndSet(a.getReference(), a.getReference() + 1, a.getStamp(), a.getStamp() + 1)){}
                    }

                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println(a.getReference());
    }
}
