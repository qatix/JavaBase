package com.qatix.base.concurrent.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    //    public static Semaphore semaphore = new Semaphore(1);
    public static Semaphore semaphore = new Semaphore(3, true);
    //如果是公平锁，且permit=1，能保证获取到的信号量顺序,A1-A2-A3，否则不能保证
    //我想得是如果permit > 1,那么多个线程都能拿到，不存在竞争问题，不存在不公平一说，公平的意思是保证在资源不足时按顺序分配

    public static void main(String[] args) {
        //启动三个线程，互相拿锁
        Thread t1 = new Thread(new SemaphoreTask());
        t1.setName("A1");
        t1.start();
        Thread t2 = new Thread(new SemaphoreTask());
        t2.setName("A2");
        t2.start();
        Thread t3 = new Thread(new SemaphoreTask());
        t3.setName("A3");
        t3.start();
    }
}

class SemaphoreTask implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
//        System.out.println(threadName + " started");
        int i = 0;
        Random r = new Random();
        while (i <= 3) {
            try {
                SemaphoreTest.semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " acquired -" + i);
            try {
                Thread.sleep(r.nextInt(1000));
                SemaphoreTest.semaphore.release();
                Thread.sleep(r.nextInt(100));//再停止随机一段时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }
}


