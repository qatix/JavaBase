package com.qatix.base.mq.rabbitmq.topic;

import com.qatix.base.mq.rabbitmq.Util.MQHelper;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogs {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException {

        Connection connection = MQHelper.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();

        String bindingKey = "abc";
        channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
        System.out.println("queueName:" + queueName);

        System.out.println(" [*] Waiting for messages.To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received  '" + envelope.getRoutingKey() + "' " + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
