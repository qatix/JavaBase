package com.qatix.base.designpattern.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 由Proxy创建一个代理 ITask 接口的代理来进行实际方法调用
 * <p>
 * cglib 是对一个小而快的字节码处理框架 ASM 的封装。 他的特点是继承于被代理类，这就要求被代理类不能被 final 修饰。
 */
public class TaskCglibProxy<T> implements MethodInterceptor {
    public T getProxy(Class clz) {
        Enhancer enhancer = new Enhancer(); //创建Enhancer来生成动态代理类
        enhancer.setSuperclass(clz);
        enhancer.setCallback(this);  //设置回调，之后所有的方法调用将会执行intercept方法
        return  (T)enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("-----> before " + method.getName());
        Object result = methodProxy.invokeSuper(o, args); //调用业务类的方法
        System.out.println("-----> after " + method.getName());
        return result;
    }
}

