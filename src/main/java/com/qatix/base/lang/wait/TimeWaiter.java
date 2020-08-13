package com.qatix.base.lang.wait;

import sun.misc.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class TimeWaiter implements Runnable {
    private final Message msg;
    private long timeout;

    public TimeWaiter(Message msg, long timeout) {
        this.msg = msg;
        this.timeout = timeout;
    }

    public static void main(String[] args) {
        int i = 2147483647;
        System.out.println(i);
        System.out.println(Integer.MAX_VALUE);
    }
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try {
                System.out.println(name + " waiting to get notified at time:" + System.currentTimeMillis());
                msg.wait(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " waiter  got notify at time:" + System.currentTimeMillis());
            //process the message
            System.out.println(name + " processed:" + msg.getMsg());
        }
    }
}
