package com.qatix.base.mq.rocketmq.consumer;

import lombok.Data;
import org.apache.rocketmq.common.message.MessageQueue;

import java.io.Serializable;
import java.util.Map;

@Data
public class ConsumerOffsetConfig implements Serializable {
    private static final long serialVersionUID = 1000L;

    private Map<MessageQueue, Long> offsetTable;
}
