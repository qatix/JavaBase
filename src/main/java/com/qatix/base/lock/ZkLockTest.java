package com.qatix.base.lock;

public class ZkLockTest {
    private static int n = 200;

    private static void secsDown() {
        System.out.println(--n);
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
                ZkLock lock = null;
                try {
                    lock = new ZkLock("localhost:2181", "zklock");
                    lock.lock();
                    secsDown();
                    System.out.println(Thread.currentThread().getName() + " is running");
                    System.out.println(Thread.currentThread().getName() + " do its work");

                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }

            }
        };

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(runnable);
            t.start();
        }
    }
}
