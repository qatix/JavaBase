package com.qatix.base.concurrent.queue;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 竞争区域，比如同时触发才能继续
 * <p>
 * The java.util.concurrent.CyclicBarrier class is a synchronization mechanism that can synchronize threads progressing through some algorithm. In other words, it is a barrier that all threads must wait at, until all threads reach it, before any of the threads can continue.
 * http://tutorials.jenkov.com/java-util-concurrent/cyclicbarrier.html
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        Runnable barrierAction1 = new Runnable() {
            public void run() {
                System.out.println("BarrierAction 1 executed");
            }
        };

        Runnable barrierAction2 = new Runnable() {
            public void run() {
                System.out.println("BarrierAction 2 executed");
            }
        };

        CyclicBarrier barrier1 = new CyclicBarrier(2, barrierAction1);
        CyclicBarrier barrier2 = new CyclicBarrier(2, barrierAction2);

        CyclicBarrierRunnable barrierRunnable1 =
                new CyclicBarrierRunnable(barrier1, barrier2);
        CyclicBarrierRunnable barrierRunnable2 =
                new CyclicBarrierRunnable(barrier1, barrier2);

        new Thread(barrierRunnable1).start();
        new Thread(barrierRunnable2).start();
    }
}

class CyclicBarrierRunnable implements Runnable {
    CyclicBarrier barrier1;
    CyclicBarrier barrier2;

    public CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) {
        this.barrier1 = barrier1;
        this.barrier2 = barrier2;
    }

    public void run() {
        try {
            Random r = new Random();
            int t = r.nextInt(500) + 500;
            Thread.sleep(t);
            System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
            this.barrier1.await();

            t = r.nextInt(500) + 500;
            Thread.sleep(t);
            System.out.println(Thread.currentThread().getName() + " waiting at barrier 2");
            this.barrier2.await();
            System.out.println(Thread.currentThread().getName() + " done");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
