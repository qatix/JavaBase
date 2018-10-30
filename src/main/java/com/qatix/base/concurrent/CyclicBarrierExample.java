package com.qatix.base.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int N = 3;
//        CyclicBarrier barrier = new CyclicBarrier(N);
        //会从多个子线程中选个一个来执行后面的Runnable
        CyclicBarrier barrier = new CyclicBarrier(N, () -> System.out.println("Current Thread is " + Thread.currentThread().getName()));
        for (int i=0;i<N;i++){
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread{
        private CyclicBarrier barrier;

        public Writer(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("Thread " + Thread.currentThread().getName() + " is writing...");
            try {
                Thread.sleep(2000);
                System.out.println("Thread " + Thread.currentThread().getName() + " finish writing");
                this.barrier.await();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("all thread finished writing");
        }
    }
}
