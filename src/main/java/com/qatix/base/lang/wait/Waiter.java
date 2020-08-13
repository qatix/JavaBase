package com.qatix.base.lang.wait;

public class Waiter implements Runnable {
    private final Message msg;

    public Waiter(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try {
                System.out.println(name + " waiting to get notified at time:" + System.currentTimeMillis());
                msg.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " waiter  got notify at time:" + System.currentTimeMillis());
            //process the message
            System.out.println(name + " processed:" + msg.getMsg());
        }
    }
}
