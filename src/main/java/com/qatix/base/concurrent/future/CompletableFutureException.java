package com.qatix.base.concurrent.future;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10,
                5, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @SneakyThrows
            @Override
            public String get() {
                System.out.println("future1 -- run");
                Thread.sleep(3000);
//                throw new RuntimeException("exception-test");
                System.out.println("future1 sleep over");
                return "ok-result";
            }
        }, threadPoolExecutor);
        future1.whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("future1-complete");
                System.out.println("s:" + s);
                System.out.println("throwable:" + throwable);
            }
        });

        future1.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("accept:" + s);
            }
        });

        future1.exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                System.out.println("exceptionlly apply");
                return null;
            }
        });
        //手动触发一个异常，让线程结束
        future1.completeExceptionally(new Throwable("test throw"));

        future1.join();

    }
}
