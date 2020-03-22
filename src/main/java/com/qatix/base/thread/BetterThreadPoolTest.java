package com.qatix.base.thread;

public class BetterThreadPoolTest {
    public static void main(String[] args) {

        BetterThreadPool threadPool = new BetterThreadPool();
        for (int i = 0; i < 50; i++) {
            int finalI = i;
            threadPool.submitTask(() -> {
                int st = 3000;
                System.out.println(finalI + "thread=" + Thread.currentThread().toString() + " sleep " + st + "ms");
                try {
                    Thread.sleep(st);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}

