package com.qatix.base.redis.lettuce;

import io.lettuce.core.KeyValue;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MultiGetTest {
    public static void main(String[] args) {

        RedisURI redisUri = RedisURI.builder()                    // <1> 创建单机连接的连接信息
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);   // <2> 创建客户端
        StatefulRedisConnection<String, String> connection = redisClient.connect();     // <3> 创建线程安全的连接
        RedisCommands<String, String> redisCommands = connection.sync();                // <4> 创建同步命令
        String[] cacheKeys = new String[]{"aa", "ab", "ac", "ad"};
        List<KeyValue<String, String>> cacheValues = redisCommands.mget(cacheKeys);
        System.out.println(cacheValues);
        for (KeyValue<String, String> kv : cacheValues) {
            if (kv.hasValue()) {
                System.out.println(kv.getKey() + "|" + kv.getValue());
            } else {
                System.out.println(kv.getKey() + " not found");
            }
        }

    }
}
