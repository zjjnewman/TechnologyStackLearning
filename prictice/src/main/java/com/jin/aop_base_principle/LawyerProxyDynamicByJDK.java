package com.jin.aop_base_principle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK提供的静态代理
 * @author jin
 */
public class LawyerProxyDynamicByJDK implements InvocationHandler {

    Object obj;

    public LawyerProxyDynamicByJDK(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("speak")){
            System.out.println("---------动态代理----------");
            System.out.println("引用法律条款");
            method.invoke(obj, args);
            System.out.println("打人是不对的");
        }
        return null;
    }
}
