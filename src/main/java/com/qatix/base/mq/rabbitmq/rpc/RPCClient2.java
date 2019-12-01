package com.qatix.base.mq.rabbitmq.rpc;

import com.qatix.base.mq.rabbitmq.Util.MQHelper;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

public class RPCClient2 {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;

    public RPCClient2() throws IOException, TimeoutException {

        connection = MQHelper.getConnection();
        channel = connection.createChannel();

        replyQueueName = channel.queueDeclare().getQueue();
    }

    public String call(String message) throws IOException, InterruptedException {
        String corrId = UUID.randomUUID().toString();
//        final String corrId = "id-" + message;
        System.out.println("call id:" + corrId);
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

        final BlockingQueue<String> response = new ArrayBlockingQueue<String>(1);
        channel.basicConsume(replyQueueName, true, new RPCConsumer(channel, corrId, response));
        return response.take();
    }

    public void close() throws IOException {
        connection.close();
    }
}

class RPCConsumer extends DefaultConsumer {

    private String corrId;
    private BlockingQueue<String> response;

    public RPCConsumer(Channel channel, String corrId, BlockingQueue<String> response) {
        super(channel);
        this.corrId = corrId;
        this.response = response;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        System.out.println("record corrId:" + corrId + " == " + properties.getCorrelationId());
        if (properties.getCorrelationId().equals(corrId)) {
            response.offer(new String(body, "UTF-8"));
        }
    }
}
