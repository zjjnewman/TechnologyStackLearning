package com.jin.concurrent.taojcp;

import javax.swing.*;

/**
 * 4-13
 */
public class Join {
    public static void main(String[] args) {
        Thread previous = null;
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Domino(previous));
            previous = thread;
            thread.start();

        }
    }

    private static class Domino implements Runnable{
        Thread thread;
        public Domino(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "启动");
            try {
                if (thread != null){
                    thread.join();
                }
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
