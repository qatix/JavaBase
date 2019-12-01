package com.qatix.base.redis.lettuce;

import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/20 5:42 PM
 */
public class TestSetGet {
    public static void main(String[] args) {
        LogRdsConfig config = new LogRdsConfig("localhost", 6379, "", 10, 5, 2, 500, 5000);
        System.out.println(config.toString());

        LogRdsPool pool = new LogRdsPool(config);

        try {
            StatefulRedisConnection<String, String> connection = pool.borrowObject();
            connection.sync().set("lettuce-key1", "lettuce-val-123-sync");
            connection.async().set("lettuce-key2", "lettuce-val-456-async");
            System.out.println("set ok");

            System.out.println("lettuce-key1:");
            System.out.println(connection.sync().get("lettuce-key1"));

            System.out.println("lettuce-key2:");
            System.out.println(connection.async().get("lettuce-key2").get());

            //必须return，否则高并发下会阻塞
            pool.returnObject(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
