package com.qatix.base.mq.rocketmq.schedule;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class ScheduledMessageProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("schedule-produer");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        for (int i = 0; i < 10; i++) {
            Message message = new Message("TestTopic", ("Hello scheduled message " + i).getBytes());
            message.setDelayTimeLevel(i % 4 + 1);
            producer.send(message);
        }
        producer.shutdown();
    }
}
