package com.jin.atguigu.jvm;

public class HeapDemo {
    public static void main(String[] args) {
        System.out.println("star...");

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("end...");
    }
}
