package com.jin.atguigu.jvm;

/**
 * P66 https://www.bilibili.com/video/BV1PJ411n7xZ?p=66
 * @author jin
 */
public class HeapDemo1 {
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
