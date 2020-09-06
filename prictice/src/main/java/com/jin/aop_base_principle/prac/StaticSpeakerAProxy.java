package com.jin.aop_base_principle.prac;

public class StaticSpeakerAProxy implements Speaker{
    Speaker a = new A();
    @Override
    public void speak() {
        System.out.println("增强前");
        a.speak();
        System.out.println("增强后");
    }
}
