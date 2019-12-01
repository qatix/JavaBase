package com.qatix.base.lock;

import org.apache.commons.lang3.RandomUtils;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class RedisLockTest {
    public static void main(String[] args) {
        final Jedis jedis = new Jedis("127.0.0.1");
        System.out.println(jedis.get("aaa"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Test(jedis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Test(jedis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Test(jedis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void Test(Jedis jedis) throws InterruptedException {
        RedisLock lock = new RedisLock(jedis);
        String lockKey = "myrkey";
        String requestId = UUID.randomUUID().toString();
        int expireTime = 300000;//300s
        Thread.sleep(RandomUtils.nextLong(100, 2000));
        boolean lockStatus = lock.getLock(lockKey, requestId, expireTime);
        System.out.println(Thread.currentThread().getName() + ":lock-status:" + lockStatus + ":" + System.currentTimeMillis());
        if (lockStatus) {
            System.out.println(Thread.currentThread().getName() + ":do some work");
            Thread.sleep(RandomUtils.nextLong(200, 3000));
            boolean releaseStatus = lock.releaseLock(lockKey, requestId);
            System.out.println(Thread.currentThread().getName() + ":release-status:" + releaseStatus + ":" + System.currentTimeMillis());
        }
    }
}
