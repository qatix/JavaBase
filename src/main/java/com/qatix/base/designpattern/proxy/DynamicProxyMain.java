package com.qatix.base.designpattern.proxy;

public class DynamicProxyMain {
    public static void main(String[] args) {
        ITask task = new TaskDynamicProxy(new Task()).getProxy();
        task.doTask();
        task.doAnotherTask();
    }
}
