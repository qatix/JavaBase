package com.qatix.base.mq.rabbitmq.rpc;

import com.qatix.base.mq.rabbitmq.Util.MQHelper;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class RPCClient {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;
    private String currentId;

    public RPCClient() throws IOException, TimeoutException {

        connection = MQHelper.getConnection();
        channel = connection.createChannel();

        replyQueueName = channel.queueDeclare().getQueue();
    }

    public String call(String message) throws IOException, InterruptedException {
        final String corrId = UUID.randomUUID().toString();
        System.out.println("call id:" + corrId);
//        resMap.put(corrId,new ResObjec(corrId));
        this.currentId = corrId;
        final RPCClient rpcClient = this;
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(3);
        channel.basicConsume(replyQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //corrId为什么不再变化，final局部变量也不可更改？
                System.out.println("record corrId:" + rpcClient.currentId + " == " + properties.getCorrelationId());
                if (properties.getCorrelationId().equals(rpcClient.currentId)) {
                    response.offer(new String(body, "UTF-8"));
                }
                System.out.println("response size:" + response.size());
            }
        });

        return response.take();
    }

    public void close() throws IOException {
        connection.close();
    }
}

