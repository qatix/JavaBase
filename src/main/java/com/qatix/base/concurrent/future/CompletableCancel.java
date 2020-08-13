package com.qatix.base.concurrent.future;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class CompletableCancel {
    public static void main(String[] args) {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(new Function<String, Object>() {
            @Override
            public <V> Function<V, Object> compose(Function<? super V, ? extends String> before) {
                return null;
            }

            @Override
            public <V> Function<String, V> andThen(Function<? super Object, ? extends V> after) {
                return null;
            }

            @Override
            public Object apply(String s) {
                System.out.println("sleep begin");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sleep over");
                return s.toUpperCase();
            }
        });
        CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");
        System.out.println("Was not canceled:" + cf.cancel(true));
        System.out.println("Was not completed exceptionally:" + cf.isCompletedExceptionally());
        System.out.println("canceled message:" + cf2.join());

    }
}
