package com.qatix.base.redis.lettuce;

import io.lettuce.core.api.StatefulRedisConnection;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/20 5:42 PM
 */
public class TestPush {

    public static void main(String[] args) {
        LogRdsConfig config = new LogRdsConfig("localhost",6379,"",10,5,2,500,5000);
        System.out.println(config.toString());

        LogRdsPool pool = new LogRdsPool(config);

        try {
            StatefulRedisConnection<String, String> connection = pool.borrowObject();

            System.out.println("before:" + connection.sync().llen("lettuce-queue"));;

            connection.async().lpush("lettuce-queue","async1");
            connection.async().lpush("lettuce-queue","async2");

            System.out.println("after:" + connection.sync().llen("lettuce-queue"));

            //必须return，否则高并发下会阻塞
            pool.returnObject(connection);

            System.out.println("push done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
