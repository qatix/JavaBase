package com.qatix.base.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Thread-Pool1-%d").build();
        //Common Thread Pool
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5, namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("schedule-run:" + new Date());
            }
        }, 5, 5, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.awaitTermination(100, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.shutdown();
        System.out.println("schedule tp close");
    }
}
