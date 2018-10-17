package com.qatix.base.mq.rocketmq.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Application: asynchronous transmission is generally used in response time sensitive business scenarios.
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("Async-producer");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        for (int i=0;i<10;i++){
            final  int index = i;
            Message message = new Message("TopicTest",
                    "TagA",
                    "OrderID188",
                    "HelloWorld".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n",index,sendResult.getMsgId());
                }

                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n",index,e);
                    e.printStackTrace();
                }
            });
        }

        long start = System.currentTimeMillis();
        long N = 100000;
        for (int i=0;i< N ;i++){
            Message msg = new Message(
                    "TopicTest3",
                    "Tag",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)

            );
            producer.send(msg, new SendCallback() {
                public void onSuccess(SendResult sendResult) {

                }

                public void onException(Throwable e) {

                }
            });
//            System.out.printf("%s%n",sendResult);
            if(i%10000==0){
                System.out.println(" >> " + i);
            }
        }
        long end = System.currentTimeMillis();
        long tps = N/(end-start)*1000;
        System.out.println("tps:" + tps); //tps:19000

        producer.shutdown();

    }
}
