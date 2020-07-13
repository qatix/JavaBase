package com.qatix.base.mq.rocketmq.transaction;

public interface ProduceOrderService {

    /**
     * 下单接口
     */
    int save(int userId, int produceId, int total);
}