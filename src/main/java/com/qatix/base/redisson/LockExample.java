package com.qatix.base.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.util.concurrent.TimeUnit;

public class LockExample {
    public static void main(String[] args) {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress("redis://127.0.0.1:6379");

        RedissonClient client = Redisson.create(config);

        while (true) {
            RLock lock = client.getLock("lock-name");
            try {
                lock.tryLock(5, 20, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName() + ":task start to execute");
                Thread.sleep(1000);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
