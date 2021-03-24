package com.qatix.base.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author tang
 */
public class ImplementInterface implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        try {
            if ("say".equals(method.getName())) {
                System.out.println("从这里开始执行say方法。");
                System.out.println("...");
                System.out.println("执行say方法结束。");
            }else if("dosomething".equals(method.getName())){
                System.out.println("从这里开始执行dosomething方法。");
                System.out.println("...");
                System.out.println("执行dosomething方法结束。");
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }

        System.out.println("执行后");
        return null;
    }
}
