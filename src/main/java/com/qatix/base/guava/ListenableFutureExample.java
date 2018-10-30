package com.qatix.base.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/30 2:08 PM
 */
public class ListenableFutureExample {
    public static void main(String[] args) throws InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> result = service.submit(() -> {
            Thread.sleep(3000);
            return Thread.currentThread().getName() + "-call-result";
        });

        Futures.addCallback(result, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("onSuccess:" + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.err.println("onFailure:" + t.toString());
            }
        });

        System.out.println("wait for done");
        Thread.sleep(5000);
        System.out.println("time up");
        service.shutdown();
        System.out.println("service is shutdown:" + service.isShutdown());
    }
}
