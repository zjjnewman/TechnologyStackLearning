package com.jin.concurrent.taojcp;

/**
 * 4-11
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread waitThread = new Thread(new Wait());
        waitThread.start();
        Thread.sleep(1000);
        Thread notifyThread = new Thread(new Notify());
        notifyThread.start();
    }

    static class Wait implements Runnable{

        @Override
        public void run() {
           synchronized (lock){
               while (flag){
                   try{
                       System.out.println(Thread.currentThread().getName() + "flag true，条件不满足，调用wait等待" );
                       lock.wait();
                       lock.notify();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               System.out.println(Thread.currentThread().getName() + "flag false");
           }
        }
    }

    static class Notify implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread().getName() + "获取到锁 notify");
                lock.notify();
//                flag = false;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            synchronized (lock){

                int i = 5;
                while (i>0){
                    System.out.println("再次获取锁");
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i --;
                }
                flag = false;
                lock.notify();
            }
        }
    }
}
