package com.qatix.base.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @Author: Logan
 * @Date: 02/08/2018 11:08 AM
 */
//@Configuration
//@EnableConfigurationProperties(LogRdsConfig.class)
public class LogRdsPool {

    private final GenericObjectPool<StatefulRedisConnection<String, String>> pool;

    public LogRdsPool(LogRdsConfig rdsConfig) {
        RedisClient client = RedisClient.create(RedisURI.create(rdsConfig.getHost(), rdsConfig.getPort()));
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(rdsConfig.getMaxIdle());
        poolConfig.setMaxTotal(rdsConfig.getMaxTotal());
        poolConfig.setMinIdle(rdsConfig.getMinIdle());
        poolConfig.setMaxWaitMillis(rdsConfig.getMaxWaitMillis());
        poolConfig.setMinEvictableIdleTimeMillis(rdsConfig.getMinEvictableIdleTimeMillis());

        pool = ConnectionPoolSupport
                .createGenericObjectPool(client::connect, poolConfig);
    }

    public StatefulRedisConnection<String, String> borrowObject() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void returnObject(StatefulRedisConnection<String, String> obj) {
        pool.returnObject(obj);
    }
}


