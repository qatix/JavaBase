package com.qatix.base.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuterPool {
    public static void main(String[] args) {
        ExecutorService executors1 = Executors.newFixedThreadPool(10);

        ExecutorService executors2 = Executors.newCachedThreadPool();

        ExecutorService executors3 = Executors.newScheduledThreadPool(10);

        ExecutorService executors4 = Executors.newSingleThreadExecutor();
    }
}
