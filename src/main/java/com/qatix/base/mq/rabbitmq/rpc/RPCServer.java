package com.qatix.base.mq.rabbitmq.rpc;

import com.qatix.base.mq.rabbitmq.Util.MQHelper;
import com.rabbitmq.client.*;

import java.io.IOException;

public class RPCServer {
    public static final String RPC_QUEUE_NAME = "rpc_queue";

    private static int fib(int n){
        if(n == 0)return 0;
        if(n== 1) return 1;
        return fib(n-1) + fib(n-2);
    }

    public static void main(String[] args) throws IOException {
        Connection connection = MQHelper.getConnection();
        final Channel channel  = connection.createChannel();

        channel.queueDeclare(RPC_QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        System.out.println(" [x] Awaiting RPC requests");

        Consumer consumer = new DefaultConsumer(channel) {
            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] body) throws IOException {
                AMQP.BasicProperties replyProps= new AMQP.BasicProperties.Builder()
                        .correlationId(basicProperties.getCorrelationId())
                        .build();
                String response = "";
                String message = new String(body,"UTF-8");

                int n = Integer.parseInt(message);
                System.out.println(" [.] fib(" + message + ")");
                System.out.println(basicProperties);
                response += fib(n);

                channel.basicPublish("",basicProperties.getReplyTo(),replyProps,response.getBytes("UTF-8"));
                channel.basicAck(envelope.getDeliveryTag(),false);
                synchronized (this){ //RabbitMq consumer worker thread notifies the RPC server owner thread
                    this.notify();
                }
            }
        };
        channel.basicConsume(RPC_QUEUE_NAME,false,consumer);

        while (true){
            synchronized (consumer){
                try {
                    consumer.wait();
                    System.out.println("wait pass");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

//        connection.close();
    }
}
