package com.jin.concurrent.juc.reftype_and_threadlocal;

import java.lang.ref.SoftReference;

/**
 * 初始的时候：
 *      eden：5.5m
 *      survivor0：0.5m
 *      survivor1：0.5m
 *      old：13.5m
 *
 * Byte[] 为啥不能分配4m的空间？byte[] 为啥可以分配4m？
 * 答：通过 jps jstat 命令观察，Byte占用永久代空间很大。见 LocationByteTest
 *
 * 内存够 不gc，内存不够就gc，所以SoftReference适合做 缓存 机制。
 */
public class T02_SoftReference {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);

        System.out.println(m.get()+"  len: " + (m.get().length/1024)+ "K");

//        m = null;

//        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(m.get());
        byte[] b = new byte[1024*1024*10];
        System.out.println(m.get());



    }
}
