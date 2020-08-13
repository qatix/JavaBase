package com.qatix.base.lang.wait;

public class WaitNotifyMain {
    public static void main(String[] args) {
        Message msg = new Message("TestMessage");
        Waiter waiter1 = new Waiter(msg);
        Waiter waiter2 = new Waiter(msg);
        new Thread(waiter1,"waiter1").start();
        new Thread(waiter2,"waiter2").start();

        Notifier notifier = new Notifier(msg);
        new Thread(notifier,"notifier").start();
        System.out.println("All threads are started");
    }
}
