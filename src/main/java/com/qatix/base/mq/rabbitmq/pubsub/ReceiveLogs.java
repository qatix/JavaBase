package com.qatix.base.mq.rabbitmq.pubsub;

import com.qatix.base.mq.rabbitmq.Util.MQHelper;
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogs {
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException {

        Connection connection = MQHelper.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
//        String queueName = "tang-sub";
        channel.queueBind(queueName, EXCHANGE_NAME, "");
//        channel.queueBind("tang-sub",EXCHANGE_NAME,"");
        System.out.println("queueName:" + queueName);

        System.out.println(" [*] Waiting for messages.To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received sub: '" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
