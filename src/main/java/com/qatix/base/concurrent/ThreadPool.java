package com.qatix.base.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.rocketmq.common.ThreadFactoryImpl;

import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        int minThreads = 3;
        int maxThreads = 30;
        int keepAlive = 5;
        ThreadPoolExecutor threadPool =   new ThreadPoolExecutor(minThreads, maxThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                new ThreadFactoryBuilder().setNameFormat("my-thread-pool-%d").build());

        for(int i=0;i<10;i++){
            int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":"+ finalI);
                }
            });
        }

        Thread.sleep(1000);
    }
}
