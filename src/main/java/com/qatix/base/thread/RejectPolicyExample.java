package com.qatix.base.thread;

import org.joda.time.LocalDateTime;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池共包括4种拒绝策略，它们分别是：AbortPolicy, CallerRunsPolicy, DiscardOldestPolicy和DiscardPolicy。
 * AbortPolicy         -- 当任务添加到线程池中被拒绝时，它将抛出 RejectedExecutionException 异常。
 * CallerRunsPolicy    -- 当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务。
 * DiscardOldestPolicy -- 当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中。
 * DiscardPolicy       -- 当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务。
 */
public class RejectPolicyExample {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 0,
                TimeUnit.MICROSECONDS,
                new LinkedBlockingDeque<Runnable>(3),
                new MyRejectedExecutionHandler());

        for (int i = 0; i < 10; i++) {
            System.out.println("添加第" + i + "个任务");
            executor.execute(new MyThread("线程" + i));
        }
        try {
            executor.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!executor.isShutdown()) {
                System.err.println("未执行完，立即关闭");
                executor.shutdownNow();
            }
        }
    }

    static class MyRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //do whatever you like
            new Thread(r, "创建新线程执行" + new Random().nextInt(10)).start();
        }
    }

    static class MyThread implements Runnable {
        String name;

        public MyThread(String name) {
            this.name = name;
            System.out.println("create-thread:" + name);
        }

        @Override
        public void run() {
            System.out.println(new LocalDateTime());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new LocalDateTime() + "-线程:" + Thread.currentThread().getName() + " 执行:" + name + "  run");
        }
    }
}
