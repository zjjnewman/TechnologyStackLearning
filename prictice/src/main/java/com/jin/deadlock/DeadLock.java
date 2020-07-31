package com.jin.deadlock;


/**
 * 本程序是对死锁的演示
 *      死锁检测方法：
 *          命令行：jps -l                  找到运行的线程pid
 *                 jcmd pid Thread.print   打印此线程的dump详细信息，会有提示是否死锁
 *                 或者用 jvisualvm 线程选项下 查看是否有死锁。
 */
public class DeadLock implements Runnable{
    private int flag = 1;
    private static final Object o1 = new Object();
    private static final Object o2 = new Object();
    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " o1");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " o2");
                }
            }
        }
        if (flag == 2) {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " o2");
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " o1");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock1 = new DeadLock();
        DeadLock deadLock2 = new DeadLock();

        deadLock1.setFlag(1);
        Thread thread1= new Thread(deadLock1, "Thread1");
        thread1.start();

        deadLock2.setFlag(2);
        Thread thread2= new Thread(deadLock2, "Thread2");
        thread2.start();
    }
}
