package com.qatix.base.mq.rabbitmq.queue;

import com.qatix.base.mq.rabbitmq.Util.MQHelper;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Receiver {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {
        Connection connection = MQHelper.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            }
        };

        channel.basicConsume(QUEUE_NAME, true, consumer);

//        channel.close();
//        connection.close();
    }
}
