package com.qatix.base.mq.rocketmq.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Application: Reliable synchronous transmission is used in extensive scenes, such as important notification messages, SMS notification, SMS marketing system, etc..
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("sync-producer");
        producer.setNamesrvAddr("localhost:9876");

        producer.start();
        System.out.println("producer start");
        for (int i=0;i<10;i++){
            Message msg = new Message(
                    "TopicTest",
                    "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)

            );

            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n",sendResult);
        }

        long start = System.currentTimeMillis();
        long N = 100000;
        for (int i=0;i< N ;i++){
            Message msg = new Message(
                    "TopicTest3",
                    "TagA",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)

            );

            SendResult sendResult = producer.send(msg);
//            System.out.printf("%s%n",sendResult);
            if(i%10000==0){
                System.out.println(" >> " + i);
            }
        }
        long end = System.currentTimeMillis();
        long tps = N/(end-start)*1000;
        System.out.println("tps:" + tps);//tps : 3000


        producer.shutdown();
        System.out.println("producer shutdown");
    }
}
