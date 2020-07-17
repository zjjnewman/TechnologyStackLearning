package com.jin.juc.reftype_and_threadlocal;

public class T01_NormalReference {

    public static void main(String[] args) {
        M m = new M();
        m = null;
        System.out.println(System.getProperty("java.version"));
        System.gc();
    }
}
