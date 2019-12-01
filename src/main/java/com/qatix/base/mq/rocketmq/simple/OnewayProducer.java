package com.qatix.base.mq.rocketmq.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Application: One-way transmission is used for cases requiring moderate reliability, such as log collection.
 */
public class OnewayProducer {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("Async-producer");
//        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message("TopicTest", "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(message);
            System.out.println("send " + i);
        }
        System.out.printf("send done");

//        long start = System.currentTimeMillis();
//        long N = 100000;
//        for (int i=0;i< N ;i++){
//            Message msg = new Message(
//                    "TopicTest3",
//                    "TagA",
//                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
//
//            );
//
//            producer.sendOneway(msg);
////            System.out.printf("%s%n",sendResult);
//            if(i%10000==0){
//                System.out.println(" >> " + i);
//            }
//        }
//        long end = System.currentTimeMillis();
//        long tps = N/(end-start)*1000;
//        System.out.println("tps:" + tps); //tps:55000 最高的吞吐量，但是消息可能丢失

        producer.shutdown();
    }
}
