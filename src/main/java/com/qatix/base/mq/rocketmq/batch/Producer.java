package com.qatix.base.mq.rocketmq.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Producer {
    public static final String TOPIC = "TopicTest";

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("broadcast-group");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message(TOPIC, "TagA", "Order001", "Hello batch 1".getBytes()));
        messages.add(new Message(TOPIC, "TagA", "Order002", "Hello batch 2".getBytes()));
        messages.add(new Message(TOPIC, "TagA", "Order003", "Hello batch 3".getBytes()));

//        producer.send(messages);
//        System.out.println("send done");

        ListSplitter splitter = new ListSplitter(messages);
        while (splitter.hasNext()) {
            List<Message> subList = splitter.next();
            System.out.println(subList);
            producer.send(subList);
        }
    }
}
