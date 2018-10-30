package com.qatix.base.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " executes done");
                countDownLatch.countDown();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " executes done");
                countDownLatch.countDown();
            }
        }).start();

        try {
            System.out.println("wait two thread excute done");
            countDownLatch.await(10, TimeUnit.SECONDS);
            System.out.println("two threads task finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("service shutdown");

    }
}
