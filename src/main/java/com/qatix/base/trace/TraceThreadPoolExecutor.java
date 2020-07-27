package com.qatix.base.trace;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TraceThreadPoolExecutor extends java.util.concurrent.ThreadPoolExecutor{
    public TraceThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    public void execute(Runnable runnable) {
        TraceRunnable traceRunnable = new TraceRunnable(runnable);
        super.execute(traceRunnable);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        TraceCallable traceCallable = new TraceCallable(task);
        return super.submit(traceCallable);
    }
}