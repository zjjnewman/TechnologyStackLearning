package com.jin.inject_reflect;

import javax.jws.WebService;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@WebService(endpointInterface = "MyService")
public class InjectReflectTest {

    public void test(){
        System.out.println("注解测试");
    }

    public static void main(String[] args) {
        WebService annotations = InjectReflectTest.class.getAnnotation(WebService.class);
        String a = annotations.endpointInterface();
        System.out.println(a);
    }
}
