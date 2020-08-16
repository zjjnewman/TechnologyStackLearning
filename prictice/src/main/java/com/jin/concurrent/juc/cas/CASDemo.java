package com.jin.concurrent.juc.cas;

import java.util.concurrent.CountDownLatch;

public class CASDemo {
    static int cnt = 0;

    public synchronized static void request() throws InterruptedException {
        Thread.sleep(5);
        cnt++;
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        int tSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(tSize);

        class casTest implements Runnable{
            @Override
            public void run() {
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        }

        for (int i = 0; i < tSize; i++) {
            Thread thread =  new Thread(new casTest(), String.valueOf(i));
            thread.start();

        }
        // 因为main不是守护线程，所以其他线程还没执行完可能main已经执行完了，所以要用 栅栏锁等所有线程执行完
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗时："+ (end - start) );
        System.out.println("结果" + cnt);
    }
}
