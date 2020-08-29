package com.qatix.base.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 由Proxy创建一个代理 ITask 接口的代理来进行实际方法调用
 */
public class TaskDynamicProxy implements InvocationHandler {

    private Task task;

    public TaskDynamicProxy(Task task) {
        this.task = task;
    }

    /**
     * 创建动态代理
     */
    public ITask getProxy() {
        return (ITask) Proxy.newProxyInstance(task.getClass().getClassLoader(), task.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("-----> before " + method.getName());
        Object ret = method.invoke(task, args); //执行真正的方法
        System.out.println("-----> after " + method.getName());
        return ret;
    }
}
