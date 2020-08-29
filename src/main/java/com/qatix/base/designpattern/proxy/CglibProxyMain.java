package com.qatix.base.designpattern.proxy;

public class CglibProxyMain {
    public static void main(String[] args) {
        ITask task = new TaskCglibProxy<ITask>().getProxy(new Task());
        task.doTask();
        task.doAnotherTask();
    }
}
