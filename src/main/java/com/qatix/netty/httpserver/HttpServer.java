package com.qatix.netty.httpserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * @see https://www.cnblogs.com/demingblog/p/9970772.html
 */
public class HttpServer {
    int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws InterruptedException {

        ServerBootstrap bootstrap = new ServerBootstrap();
        //调整线程数，再压测，可看到不同的响应能力
        EventLoopGroup boss = new NioEventLoopGroup(10);
        EventLoopGroup work = new NioEventLoopGroup(60);
        bootstrap.group(boss, work)
                .handler(new LoggingHandler(LogLevel.DEBUG))
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer());

        ChannelFuture cf = bootstrap.bind(new InetSocketAddress(port)).sync();
        System.out.println("Server start up on port:" + port);
        cf.channel().closeFuture().sync();

    }
}
