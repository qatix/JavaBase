package com.qatix.netty.staticproxy;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {

    private JedisPool jedisPool = null;

    public RedisUtil() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(10);
            config.setMaxIdle(2);
            config.setMaxWaitMillis(5000);
            config.setTestOnBorrow(false);
            String address = "127.0.0.1";
            int port = 6379;
            int timeOut = 3000;
            jedisPool = new JedisPool(config, address, port, timeOut, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RedisUtil redisUtil = new RedisUtil();
        Jedis jedis = redisUtil.getJedis();
        System.out.println(jedis.get("aaa"));
        System.out.println(jedis.set("aaa", "123"));
        System.out.println(jedis.get("aaa"));
    }

    public Jedis getJedis() {
        if (jedisPool != null) {
            return jedisPool.getResource();
        }
        return null;
    }
}