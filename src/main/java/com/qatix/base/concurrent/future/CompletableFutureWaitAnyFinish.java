package com.qatix.base.concurrent.future;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CompletableFutureWaitAnyFinish {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                5, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @SneakyThrows
            @Override
            public String get() {
                Thread.sleep(100);
                return "future1-result";
            }
        },threadPoolExecutor);
        future1.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("future1-complete");
                System.out.println("s:"+s);
                System.out.println("throwable:"+throwable);
            }
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @SneakyThrows
            @Override
            public String get() {
                System.out.println("future2 -- run");
                Thread.sleep(200);
                return "future2-result";
            }
        },threadPoolExecutor);
        future2.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("future1-complete");
                System.out.println("s:"+s);
                System.out.println("throwable:"+throwable);
            }
        });

        CompletableFuture<Object> all = CompletableFuture.anyOf(future1,future2);
        System.out.println(System.currentTimeMillis() + ":阻塞");
        all.join();
        System.out.println(System.currentTimeMillis() + ":阻塞结束");
        System.out.println("all job done");

    }
}
