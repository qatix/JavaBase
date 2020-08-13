package com.qatix.base.concurrent.future;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                5, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy());

        Future<String> future = threadPoolExecutor.submit(() -> {
            Thread.sleep(1000);
            return "test-future";
        });

        System.out.println("before:" + LocalDateTime.now());
        System.out.println("done:"+future.isDone()+" canceled:"+future.isCancelled());
        System.out.println(future.get());
        System.out.println("after:" + LocalDateTime.now());
        System.out.println("done:"+future.isDone()+" canceled:"+future.isCancelled());
    }
}
