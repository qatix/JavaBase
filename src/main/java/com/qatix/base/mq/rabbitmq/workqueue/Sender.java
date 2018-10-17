package com.qatix.base.mq.rabbitmq.workqueue;

import com.qatix.base.mq.rabbitmq.Util.MQHelper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class Sender {

    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = MQHelper.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        int i = 0;
        while (i++ < 10) {
            String message = "hello rabbitmq " + i + " - " + new Date() + (i%3 == 1 ? "lol" : "");
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

        channel.close();
        connection.close();
    }
}
