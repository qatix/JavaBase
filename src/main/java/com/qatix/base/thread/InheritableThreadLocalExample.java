package com.qatix.base.thread;

public class InheritableThreadLocalExample {

    static ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
    static ThreadLocal<Integer> threadLocal2 = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        testNormal();
        testInheritable();
    }

    private static void testInheritable() {
        threadLocal2.set(100);
        new Thread(()->{
            System.out.println(Thread.currentThread()+"== inherit: "+threadLocal2.get());
        }).start();
    }

    private static void testNormal() {
        threadLocal1.set(100);
        new Thread(()->{
            System.out.println(Thread.currentThread()+"== normal:"+threadLocal1.get());
        }).start();
    }
}
