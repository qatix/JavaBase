package com.qatix.base.lock;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * 基于redis实现分布式锁
 * https://www.cnblogs.com/love-cj/p/8242439.html
 */
public class RedisLock {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final int MAX_TRY_TIMES = 10;

    private Jedis jedis;

    public RedisLock(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     * block
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    public boolean getLock(String lockKey, String requestId, int expireTime) {
        int tryTimes = 0;
        //最多尝试MAX_TRY_TIMES次
        while (tryTimes++ < MAX_TRY_TIMES) {
            String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//            System.out.println(Thread.currentThread().getName() + ":get-lock:" + result);
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        return false;
    }

    /**
     * non-block
     * @param lockKey
     * @param requestId
     * @param expireTime
     * @return
     */
    public boolean tryGetLock(String lockKey, String requestId, int expireTime) {
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//        System.out.println(Thread.currentThread().getName() + ":get-lock:" + result);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * non-block
     * @param lockKey
     * @param requestId
     * @return
     */
    public boolean tryReleaseLock(String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
//        System.out.println(Thread.currentThread().getName() + ":release-lock:" + result);
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    /**
     * block
     * @param lockKey
     * @param requestId
     * @return
     */
    public boolean releaseLock(String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        int tryTimes = 0;
        //最多尝试MAX_TRY_TIMES次
        while (tryTimes++ < MAX_TRY_TIMES) {
            Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
//            System.out.println(Thread.currentThread().getName() + ":release-lock:" + result);
            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
        }
        return false;
    }
}
