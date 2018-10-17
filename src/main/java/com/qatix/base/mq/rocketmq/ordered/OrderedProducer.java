package com.qatix.base.mq.rocketmq.ordered;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

public class OrderedProducer {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("ordered-producer");
        producer.setNamesrvAddr("localhost:9876");
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        producer.start();
        for (int i = 0; i < 10; i++) {
            int orderId = i % 10;
            Message message = new Message("TopicTest2", tags[i % tags.length], "KEY" + i,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    Integer id = (Integer) arg;
                    int index = id % mqs.size(); //此处返回队列的标号，如果只有一个则是全局有序，多个是局部有序
                    System.out.println("mqs size:" + mqs.size());//默认是4，怎么来的？好像是配置文件中指定的
                    System.out.println("id:" + id + "," + index+"," + msg);
//                    return mqs.get(7);//IndexOutOfBoundsException: Index: 7, Size: 4
                    return mqs.get(index);
//                    return mqs.get(0); //只有一个队列
                }
            }, orderId);
            System.out.printf("%s%n", sendResult);
        }

        producer.shutdown();
    }
}

class TQ implements MessageQueueSelector{

    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
        return null;
    }
}
