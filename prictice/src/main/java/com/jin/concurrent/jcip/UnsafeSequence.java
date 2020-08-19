package com.jin.concurrent.jcip;

import com.jin.concurrent.thread.ThreadJoin;
import net.jcip.annotations.NotThreadSafe;

import java.util.HashSet;
import java.util.Set;

public class UnsafeSequence {
    private static int value;
    public int getValue(){
        return value++;
    }

    public static void main(String[] args) throws InterruptedException {
        UnsafeSequence unsafeSequence = new UnsafeSequence();
        HashSet set = new HashSet();

/*
        // 用Lamda表达式 开启线程
        for (int i = 0; i < 10; i++) {

            new Thread(()->{
                int j = 0;
                while( j != 100){
                    int a = unsafeSequence.getValue();
                    set.add(a);
                    j++;
                }

            }).start();
        }
*/

        // 1.用继承Thread开启线程
/*
        for (int i = 0; i < 10; i++) {
            ThreadTest threadTest = new ThreadTest(unsafeSequence, set);
            threadTest.start();
        }
*/

        // 用实现runnable
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new ThreadTest1(unsafeSequence, set));
            thread.start();
//            thread.join();
        }



        while (true){
            Thread.sleep(2000);
            System.out.println(set.size());
        }



    }
}

class ThreadTest extends Thread{

    UnsafeSequence unsafeSequence;
    Set set;
    public ThreadTest(UnsafeSequence unsafeSequence, Set set) {
        this.unsafeSequence = unsafeSequence;
        this.set =  set;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int a = unsafeSequence.getValue();
            set.add(a);
        }
    }
}

class ThreadTest1 implements Runnable{

    UnsafeSequence unsafeSequence;
    Set set;
    public ThreadTest1(UnsafeSequence unsafeSequence, Set set) {
        this.unsafeSequence = unsafeSequence;
        this.set =  set;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int a = unsafeSequence.getValue();
            set.add(a);
        }
    }
}