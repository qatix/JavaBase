package com.qatix.base.redis.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class PoolTest {
    public static void main(String[] args) {

        JedisPoolConfig cfg = new JedisPoolConfig();
        cfg.setMaxTotal(10);
        cfg.setMaxIdle(5);
        cfg.setMaxWaitMillis(2000);
        cfg.setMinEvictableIdleTimeMillis(5000);
        JedisPool pool = new JedisPool(cfg, "localhost");

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            /// ... do stuff here ... for example
            jedis.set("foo", "bar");
            String foobar = jedis.get("foo");
            System.out.println(foobar);
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
            System.out.println(sose);
        } finally {
            // You have to close jedis object. If you don't close then
            // it doesn't release back to pool and you can't get a new
            // resource from pool.
            if (jedis != null) {
                jedis.close();
            }
        }
/// ... when closing your application:
        pool.close();
    }
}
