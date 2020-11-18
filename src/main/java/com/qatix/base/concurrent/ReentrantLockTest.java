package com.qatix.base.concurrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    public static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        //启动三个线程，互相拿锁
        new Thread(new Task()).start();
        new Thread(new Task()).start();
        new Thread(new Task()).start();
    }

    static class Task implements Runnable {
        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
//        System.out.println(threadName + " started");
            int i = 0;
            Random r = new Random();
            while (i <= 3) {
                ReentrantLockTest.lock.lock();
                System.out.println(threadName + " get lock-" + i);
                try {
                    Thread.sleep(r.nextInt(1000));
                    ReentrantLockTest.lock.unlock();
                    Thread.sleep(r.nextInt(100));//再停止随机一段时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            System.out.println(threadName + " release lock");
                i++;
            }
        }
    }
}




