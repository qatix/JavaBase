package com.qatix.base.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class BasicSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(()->{
            try {
                System.out.println("available-before:"+semaphore.availablePermits());
                semaphore.acquire();
                System.out.println("available-after-required:"+semaphore.availablePermits());
                semaphore.drainPermits();
                System.out.println("available-after-drain:"+semaphore.availablePermits());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        });
    }
}
