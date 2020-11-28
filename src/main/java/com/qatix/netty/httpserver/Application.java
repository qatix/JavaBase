package com.qatix.netty.httpserver;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        HttpServer server = new HttpServer(9021);
        server.start();
    }
}
