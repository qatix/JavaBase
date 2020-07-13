package com.qatix.base.mq.rocketmq.transaction;

public interface ProduceService {
    void updateStore(Integer productId, Integer total, String key);
}
