package com.qatix.base.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import lombok.extern.java.Log;

import java.time.LocalDateTime;
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
                    log.info(">>" + notification.getKey() + "|" + notification.getValue());
                    log.info(">>>" + notification.toString());
                })
                .build(
                        new CacheLoader<String, String>() {
                            @Override
                            public String load(String key) {
                                log.info("load for key:" + key);
                                return "key-default-" + key + "value-" + LocalDateTime.now();
//                                return createExpensiveGraph(key);
                            }
                        });

        String key1 = "cache-test-key";
        try {
            String value = cache.get(key1);
            System.out.println("val:" + value);

            cache.refresh(key1); //触发removal

            value = cache.get(key1);
            System.out.println("val2:" + value);

            cache.put(key1, "new-put-value");//触发removal

            value = cache.get(key1);
            System.out.println("val3:" + value);

            value = cache.get(key1);
            System.out.println("val4:" + value);

            value = cache.get(key1);
            System.out.println("val5:" + value);

            System.out.println(cache.stats());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("map:");
        System.out.println(cache.asMap());

        System.out.println("size :" + cache.size());
        System.out.println("status, hitCount:" + cache.stats().hitCount()
                + ", missCount:" + cache.stats().missCount());
        System.out.println(cache.stats());

//        Thread.sleep(10000);

    }
}
/**/