package com.qatix.base.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {

        int N = 5;
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < N; i++) {
            new Worker(i + 1, semaphore).start();
        }

    }

    static class Worker extends Thread {
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("worker " + this.num + " get a machine and start producing");
                Thread.sleep(2000);
                System.out.println("worker " + this.num + " release machine");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
