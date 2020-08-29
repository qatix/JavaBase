package com.qatix.base.concurrent.future;

import java.util.concurrent.CompletableFuture;

/**
 * 两种渠道完成同一个事情，就可以调用这个方法，找一个最快的结果进行处理，最终有返回值。
 */
public class ApplyEither {
    public static void main(String[] args){

        String result = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Boy";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Girl";
        }),(s)->{return s;}).join();
        System.out.println(result);
    }
}
