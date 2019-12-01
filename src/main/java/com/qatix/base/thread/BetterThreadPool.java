package com.qatix.base.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 8:16 PM
 */
public class BetterThreadPool {

    public static void main(String[] args) {
        //method1
//        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().daemon(true).build());

        //method2
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();

        //common thread pool
        ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " is runing");
            });
        }

        pool.shutdown();
    }
}
