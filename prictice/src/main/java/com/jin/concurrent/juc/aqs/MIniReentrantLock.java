package com.jin.concurrent.juc.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class MIniReentrantLock implements Lock{


    /**
     * state
     * 0 表示未加锁
     * >0 表示枷锁
     */
    private volatile int state = 0;

    /**
     * 需要有个变量保存当前持有锁的进程
     */
    private Thread exclusiveOwnerThread;

    /**
     * 需要有队头队尾节点维护队列
     * head 当前可以得到锁的进程
     */
    Node head;
    Node tail;

    /**
     * 需要有个数据结构保存，要竞争这把锁的数据结构
     */
    static final class Node{
        // 封装的线程
        Thread thread;
        // 队列前后指针
        Node prev;
        Node next;
    }





    @Override
    public void lock() {

    }

    /**
     * 竞争资源的方法
     * 1
     */
    private void acquired(int arg){

    }



    @Override
    public void unlock() {

    }

    private void setHead(Node node){
        // 标示得到了锁，所以线程可以置为空
        this.head = node;
        node.thread = null;
    }

    /**
     * cas版的getter setter
     */
    private static final Unsafe unsafe;
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;

    static {
        try {
            Field f = null;
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            stateOffset = unsafe.objectFieldOffset(MIniReentrantLock.class.getField("stateOffset"));
            headOffset = unsafe.objectFieldOffset(MIniReentrantLock.class.getField("headOffset"));
            tailOffset = unsafe.objectFieldOffset(MIniReentrantLock.class.getField("tailOffset"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    private final boolean compareAndSetHead(Node update){
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }
    private final boolean compareAndSetTail(Node expect, Node update){
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }
    private final boolean compareAndSetStat(int expect, int update){
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    public static void main(String[] args) {
        System.out.println(" ");
    }

}
