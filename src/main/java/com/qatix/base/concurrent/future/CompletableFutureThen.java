package com.qatix.base.concurrent.future;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CompletableFutureThen {
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

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @SneakyThrows
            @Override
            public String get() {
                System.out.println("future2 -- run");
                Thread.sleep(100);
                return "future2-result";
            }
        },threadPoolExecutor);

        System.out.println("before:"+future1.isDone()+" canceled:"+future1.isCancelled());
        future1.thenRun(()->{
            System.out.println("run after future1---111");
        }).thenRun(()->{
            System.out.println("run after future1---222");
        });

        future1.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("future1-complete");
                System.out.println("s:"+s);
                System.out.println("throwable:"+throwable);
            }
        });
        System.out.println("future2-join:"+future2.join());
        Thread.sleep(1000);
        System.out.println("done:"+future1.isDone()+" canceled:"+future1.isCancelled());
    }
}
