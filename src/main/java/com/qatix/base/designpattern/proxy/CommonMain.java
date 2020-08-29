package com.qatix.base.designpattern.proxy;

public class CommonMain {
    public static void main(String[] args) {
        ITask task = new Task();
        task.doTask();
        task.doAnotherTask();
    }
}
