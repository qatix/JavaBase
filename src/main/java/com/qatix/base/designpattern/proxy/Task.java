package com.qatix.base.designpattern.proxy;

/**
 * @author tang
 */
public class Task implements ITask {

    public Task() {
        System.out.println("Task Constructor");
    }

    @Override
    public void doTask() {
        System.out.println("doTask invoked");
    }

    @Override
    public void doAnotherTask() {
        System.out.println("doAnotherTask invoked");
    }
}
