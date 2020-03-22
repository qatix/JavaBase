package com.qatix.base.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 8:16 PM
 */
public class BetterThreadPool {

    //最小线程数
    private static final int CORE_POOL_SIZE = 30;
    //最大线程数
    private static final int MAX_POOL_SIZE = 50;
    //idle线程的keepalive时间
    private static final long KEEPALIVE_TIME = 10L;
    //最大队列长度
    private static final int MAX_QUEUE_CAPACITY = 1024;

    private ExecutorService executorService;

    public BetterThreadPool() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Content-Import--Pool-%d").build();
        //Common Thread Pool
        this.executorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
                KEEPALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(MAX_QUEUE_CAPACITY), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        System.out.println("save thread pool");
    }

    public void submitTask(Runnable task) {
        this.executorService.submit(task);
    }

    public Future<?> submitCallable(Callable<?> callable) {
        return this.executorService.submit(callable);
    }
}