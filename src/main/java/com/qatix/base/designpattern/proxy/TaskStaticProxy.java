package com.qatix.base.designpattern.proxy;

public class TaskStaticProxy implements ITask {
    private Task task;

    public TaskStaticProxy(Task task) {
        this.task = task;
    }

    @Override
    public void doTask() {
        System.out.println("do something before1");
        task.doTask();
        System.out.println("do something after1");

    }

    @Override
    public void doAnotherTask() {
        System.out.println("do something before2");
        task.doAnotherTask();
        System.out.println("do something after2");
    }
}
