package com.qatix.base.designpattern.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 由Proxy创建一个代理 ITask 接口的代理来进行实际方法调用
 */
public class TaskCglibProxy<T> implements MethodInterceptor {
    /**
     * 获取代理对象
     *
     * @param object 对象
     * @return proxy 对象
     */
    public T getProxy(T object) {
        Enhancer enhancer = new Enhancer(); //创建Enhancer来生成动态代理类
        enhancer.setSuperclass(object.getClass());
        enhancer.setCallback(this);  //设置回调，之后所有的方法调用将会执行intercept方法
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("-----> before " + method.getName());
        Object result = methodProxy.invokeSuper(o, args); //调用业务类的方法
        System.out.println("-----> after " + method.getName());
        return result;
    }
}

