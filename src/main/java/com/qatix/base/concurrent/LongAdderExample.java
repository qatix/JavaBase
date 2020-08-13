package com.qatix.base.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderExample {
    public static void main(String[] args) throws InterruptedException {
        int minThreads = 3;
        int maxThreads = 30;
        int keepAlive = 5;
        ThreadPoolExecutor threadPool =   new ThreadPoolExecutor(minThreads, maxThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadFactoryBuilder().setNameFormat("my-thread-pool-%d").build());

        LongAdder adder = new LongAdder();
        for (int i = 0; i < 10 ; i++) {
            threadPool.submit(()->{
               adder.add(1);
            });
        }
        threadPool.awaitTermination(5,TimeUnit.SECONDS);
        System.out.println("longValue:"+adder.longValue());
        System.out.println("intValue:"+adder.intValue());
        System.out.println("sum:"+adder.sum());


    }
}
