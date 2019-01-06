package com.qatix.base.guava;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.curator.shaded.com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class RateLimiterExample {
    public static void main(String[] args) throws InterruptedException {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        RateLimiter rateLimiter = RateLimiter.create(3);

        ScheduledExecutorService es = new ScheduledThreadPoolExecutor(5, new BasicThreadFactory.Builder().daemon(false).build());

        for (int i = 0; i < 5; i++) {
            es.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "-tryGetOne:" + df.format(LocalDateTime.now()));
                rateLimiter.tryAcquire();
                System.out.println(Thread.currentThread().getName() + "-getLock:" + df.format(LocalDateTime.now()));
            });
        }


        ExecutorService es2 = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 2; i++) {
            es2.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "-tryGetFive:" + df.format(LocalDateTime.now()));

                rateLimiter.acquire(5);
                System.out.println(Thread.currentThread().getName() + "-getLock:" + df.format(LocalDateTime.now()));

            });
        }

        es.awaitTermination(5, TimeUnit.SECONDS);
        es.shutdown();
        System.out.println("pool 1  shutdown");

        es2.awaitTermination(10, TimeUnit.SECONDS);
        es2.shutdown();
        System.out.println("pool 2 shutdown");
    }
}
