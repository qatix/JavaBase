package com.qatix.base.guava.cache;

import com.google.common.cache.*;
import lombok.extern.java.Log;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/30 10:56 AM
 */
@Log
public class Example {
    public static void main(String[] args) throws InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(5)
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .removalListener((RemovalListener<String, String>) notification -> {
                    log.info("removal");
                    log.info(notification.getKey() + "|" + notification.getValue());
                    log.info(notification.toString());
                })
                .build(
                        new CacheLoader<String,String>() {
                            @Override
                            public String load(String key) {
                                log.info("load for key:" + key);
                                return "key-default-"+key + "value";
//                                return createExpensiveGraph(key);
                            }
                        });

        String key1 = "cache-test-key";
        try {
            String value = cache.get(key1);
            System.out.println("val:" + value);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        cache.

        Thread.sleep(10000);

    }
}
