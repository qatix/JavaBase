package com.qatix.base.concurrent.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Combine {
    public static void main(String[] args) {
        String original = "TestMsg";

        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1 + s2));
        System.out.println("result:" + result);


        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s))
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                        (s1, s2) -> s1 + "|" + s2);
        System.out.println("before:" + cf.getNumberOfDependents()); //0??
        System.out.println(cf.join());
        System.out.println("after:" + cf.getNumberOfDependents());//0

        //compose
        CompletableFuture cf2 = CompletableFuture.completedFuture(original).thenApply(s -> delayedUpperCase(s))
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(s -> delayedLowerCase(s))
                        .thenApply(s -> upper + s));
        System.out.println("compose-result:" + cf2.join());

    }

    private static String delayedLowerCase(String s) {
        if (s == null) {
            return "NULL";
        }
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s.toLowerCase();
    }

    private static String delayedUpperCase(String s) {
        if (s == null) {
            return "NULL";
        }
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s.toUpperCase();
    }
}
