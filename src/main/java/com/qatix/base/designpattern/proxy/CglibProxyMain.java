package com.qatix.base.designpattern.proxy;

public class CglibProxyMain {
    public static void main(String[] args) {
        //cglib 是对一个小而快的字节码处理框架 ASM 的封装。 他的特点是继承于被代理类，这就要求被代理类不能被 final 修饰。
        ITask task2 = new TaskCglibProxy<ITask>().getProxy(Task.class);
        task2.doTask();
        task2.doAnotherTask();
    }
}
