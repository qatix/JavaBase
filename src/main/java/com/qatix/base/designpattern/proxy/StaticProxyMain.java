package com.qatix.base.designpattern.proxy;

public class StaticProxyMain {
    public static void main(String[] args) {
        ITask task = new TaskStaticProxy(new Task());
        task.doTask();
        task.doAnotherTask();
    }
}
