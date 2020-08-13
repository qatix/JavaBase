package com.qatix.base.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureStage {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.runAsync(() -> {
            System.out.println("stage1");
        }).thenRunAsync(() -> {
            System.out.println("stage2");
        }).thenRun(() -> {
            System.out.println("stage3");
        });

        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
            return s.toUpperCase();
        });
        System.out.println(cf.getNow("default-val"));

    }
}
