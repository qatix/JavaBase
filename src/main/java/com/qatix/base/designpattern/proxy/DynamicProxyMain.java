package com.qatix.base.designpattern.proxy;

import java.lang.reflect.Proxy;

public class DynamicProxyMain {
    public static void main(String[] args) {
        ITask task = new TaskDynamicProxy(new Task()).getProxy();
        task.doTask();
        task.doAnotherTask();


        GeneralDynamicProxy handle = new GeneralDynamicProxy(Task.class);
        ITask subject = (ITask) Proxy.newProxyInstance(DynamicProxyMain.class.getClassLoader(), new Class[]{ITask.class}, handle);
        subject.doTask(); ;
    }
}
