package com.jin.aop_base_principle.prac;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
    Object obj;
    public DynamicProxy(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("speak")){
            System.out.println("动态代理");
            method.invoke(obj);
            System.out.println("动态代理结束");
        }
        return null;
    }
}
