package com.jin.atguigu.jvm;
/**
 * https://www.bilibili.com/video/BV1PJ411n7xZ?p=69
 *
 * 1. 设置堆空间
 * -Xms
 *      -X jvm运行参数
 *      ms memory start
 * -Xmx
 *
 * 2.默认堆空间大小
 *      初始 物理内存的 1/64
 *      最大 1/4
 *
 * @author jin
 */
public class HeapSpaceInitial {
    public static void main(String[] args) {
        // 返回虚拟机堆中的总容量
        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        // 返回虚拟机试图使用的最大堆内存
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println( "initialMemory: " + initialMemory + "M");
        System.out.println("maxMemory: " + maxMemory + "M");

//        System.out.println("系统内存：" + initialMemory * 64.0 / 1024 + "G");
//        System.out.println("系统内存：" + maxMemory * 4.0 / 1024 + "G");

        try {
            Thread.sleep(50000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
