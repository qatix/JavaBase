package com.qatix.base.concurrent.queue;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        Waiter waiter = new Waiter(latch);

        Decrementer decrementer = new Decrementer(latch);
        new Thread(waiter).start();
        new Thread(decrementer).start();
        Thread.sleep(4000);
    }
}

class Waiter implements Runnable {
    CountDownLatch latch;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("waiter started");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("waiter released");
    }
}

class Decrementer implements Runnable{
    CountDownLatch latch;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try{
            Thread.sleep(1000);
            this.latch.countDown();
            System.out.println("countdown1");

            Thread.sleep(1000);
            this.latch.countDown();
            System.out.println("countdown2");

            Thread.sleep(1000);
            this.latch.countDown();
            System.out.println("countdown3");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}