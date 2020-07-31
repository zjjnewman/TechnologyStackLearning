package com.jin.concurrent.juc.reftype_and_threadlocal;

public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
