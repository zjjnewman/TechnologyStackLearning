package com.jin.aop_base_principle.prac;


import java.lang.reflect.Proxy;

public class Demo {
    public static void main(String[] args) {
        Speaker speaker = new A();
        DynamicProxy d = new DynamicProxy(speaker);
        Speaker o = (Speaker) Proxy.newProxyInstance(Demo.class.getClassLoader(), new Class[]{Speaker.class}, d);
        o.speak();
    }
}
