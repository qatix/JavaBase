package com.qatix.base.thread;

import lombok.SneakyThrows;

public class ThreadInterrupt {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("线程启动了");
                while (!isInterrupted()) {
//                    Thread.sleep(100);
                    System.out.println(isInterrupted());//调用 interrupt 之后为true
                }
                System.out.println("线程结束了");
            }
        };
        thread.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        System.out.println("线程是否被中断：" + thread.isInterrupted());//true
        System.out.println("主线程结束");


        System.out.println("第一次interrupted：" + Thread.interrupted()); //false
        System.out.println("第二次interrupted：" + Thread.interrupted()); //false

        /**
         * 　interrupted()是静态方法：内部实现是调用的当前线程的isInterrupted()，并且会重置当前线程的中断状态。
         *
         * 　　　　测试当前线程是否已经中断（静态方法）。返回的是上一次的中断状态，并且会清除该状态，所以连续调用两次，第一次返回true，第二次返回false。
         *
         * 　　isInterrupted()是实例方法，是调用该方法的对象所表示的那个线程的isInterrupted()，不会重置当前线程的中断状态
         *
         * 　　　　测试线程当前是否已经中断，但是不能清除状态标识。
         */
        Thread.currentThread().interrupt();
        System.out.println("第一次interrupted：" + Thread.interrupted()); //true
        System.out.println("第二次interrupted：" + Thread.interrupted()); //false
    }
}
