package com.jin.concurrent.juc.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *
 * {@link java.util.concurrent.locks.AbstractQueuedSynchronizer}
 *      {@link java.util.concurrent.locks.ReentrantLock}
 *
 * {@link java.util.concurrent.locks.ReadWriteLock}
 *      {@link java.util.concurrent.locks.ReentrantReadWriteLock}
 *
 */
public class TestLock {


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        try {
            lock.lock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


}
