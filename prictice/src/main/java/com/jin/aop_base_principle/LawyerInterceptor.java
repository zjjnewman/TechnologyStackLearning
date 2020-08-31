package com.jin.aop_base_principle;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 用cglib实现的动态代理
 * @author jin
 */
public class LawyerInterceptor implements MethodInterceptor {

    Object obj;

    public LawyerInterceptor(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        if(method.getName().equals("speak")){
            System.out.println("---------cglib动态代理----------");
            System.out.println("引用法律条款");
            method.invoke(obj, args);
            System.out.println("打人是不对的");
        }
        return null;
    }
}
