package com.qatix.base.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.*;

@Slf4j
public class TwoThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Thread-Pool1-%d").build();
        //Common Thread Pool
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 10,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(200), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


        ThreadFactory namedThreadFactory2 = new ThreadFactoryBuilder()
                .setNameFormat("Thread-Pool2-%d").build();
        //Common Thread Pool
        ThreadPoolExecutor executorServic2 = new ThreadPoolExecutor(3, 5,
                10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100), namedThreadFactory2, new ThreadPoolExecutor.AbortPolicy());


        BlockingQueue<TaskResult> resultQueue = new ArrayBlockingQueue<>(1024);

        log.info("create task");
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Task(i, resultQueue));
        }

        log.info("create-result-task");
        for (int i = 0; i < 13; i++) {
            executorServic2.submit(new ResultTask(resultQueue));
        }

        executorServic2.awaitTermination(30, TimeUnit.SECONDS);
        executorServic2.shutdownNow();
        executorService.shutdownNow();
    }

    @Data
    @Accessors(chain = true)
    public static class TaskResult {
        private int num;
        private Integer sum;
        private Integer errCode;
        private String errMsg;
    }

    public static class Task implements Runnable {

        private int num;

        private BlockingQueue<TaskResult> resultBlockingQueue;

        public Task(int num, BlockingQueue<TaskResult> taskResults) {
            this.num = num;
            this.resultBlockingQueue = taskResults;
        }

        @Override
        public void run() {
            int ret = 0;
            int i = 0;
            while (i < this.num) {
                ret += i++;
            }
            try {
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + " cal:{}={}", this.num, ret);
            resultBlockingQueue.add(new TaskResult().setNum(this.num).setSum(ret).setErrCode(0));
        }
    }


    public static class ResultTask implements Runnable {

        private BlockingQueue<TaskResult> blockingQueue;

        public ResultTask(BlockingQueue<TaskResult> queue) {
            this.blockingQueue = queue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    TaskResult taskResult = this.blockingQueue.poll(30, TimeUnit.SECONDS);
                    log.info("get-result:" + taskResult.toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}
