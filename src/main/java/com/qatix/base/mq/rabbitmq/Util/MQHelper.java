package com.qatix.base.mq.rabbitmq.Util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQHelper {
    public static Connection getConnection() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constant.SERVER_HOST);
        try {
            Connection connection = factory.newConnection();
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
}
