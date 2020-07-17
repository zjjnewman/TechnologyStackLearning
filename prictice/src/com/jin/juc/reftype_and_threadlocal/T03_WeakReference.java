package com.jin.juc.reftype_and_threadlocal;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 */
public class T03_WeakReference {

    public static void main(String[] args) {
        Reference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
    }
}
