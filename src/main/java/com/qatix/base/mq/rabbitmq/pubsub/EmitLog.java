package com.qatix.base.mq.rabbitmq.pubsub;

import com.qatix.base.mq.rabbitmq.Util.MQHelper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class EmitLog {
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = MQHelper.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String message = "hello rabbitmq pubsub - " + new Date();
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Publish '" + message + "'");

        channel.close();
        connection.close();
    }
}
