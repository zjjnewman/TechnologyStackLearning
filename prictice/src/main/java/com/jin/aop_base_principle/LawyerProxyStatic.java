package com.jin.aop_base_principle;

public class LawyerProxyStatic implements Speaker {

    ZhangSan zhangSan = new ZhangSan();

    @Override
    public void speak() {
        System.out.println("---------静态代理----------");
        System.out.println("引用法律条款");
        zhangSan.speak();
        System.out.println("打人是不对的");
    }
}
