package com.jin.aop_base_principle;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * 静态代理示例
 * @author jin
 */
class ProxyDemo{
    public static void main(String[] args) {
        // 静态代理
        Speaker speaker =  new LawyerProxyStatic();
        speaker.speak();

        // 动态代理
        LawyerProxyDynamicByJDK lawyerProxy = new LawyerProxyDynamicByJDK(new ZhangSan());
        Speaker speaker1 = (Speaker) Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(),new Class[]{Speaker.class}, lawyerProxy);
        speaker1.speak();

        //CGLib 第三方动态代理
        LawyerInterceptor lawyerInterceptor = new LawyerInterceptor(new ZhangSan());
        ZhangSan zhangSan = (ZhangSan) Enhancer.create(ZhangSan.class, lawyerInterceptor);
        zhangSan.speak();

        //CGLib 第三方动态代理
        LawyerInterceptor lawyerInterceptor1 = new LawyerInterceptor(new LiSi());
        LiSi liSi = (LiSi) Enhancer.create(LiSi.class, lawyerInterceptor1);
        liSi.speak();

    }
}