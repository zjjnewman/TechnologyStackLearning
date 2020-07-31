package com.jin.concurrent.thread;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadJoin {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1执行了");
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2执行了");
        });


        Thread t3 = new Thread(() -> {
            try {
                t2.join();
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t3执行了");
        });

        t1.start();
        t2.start();
        t3.start();
    }


}