package com.qatix.base.mq.rabbitmq.rpc;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        RPCClient client = new RPCClient();

        System.out.println(" [x] Requesting fib(15)");
        String response = client.call("15");
        System.out.println(" [.] Got '" + response + "'");

        //只能取出一个，暂时没解决
        System.out.println(" [x] Requesting fib(10)");
        response = client.call("10");
        System.out.println(" [.] Got '" + response + "'");


        System.out.println(" [x] Requesting fib(20)");
        response = client.call("20");
        System.out.println(" [.] Got '" + response + "'");
        client.close();
    }
}
