package com.qatix.base.netty.proxyserver;

import com.qatix.base.http.HttpClientUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wenzhihuai
 * @since 2018/9/4 22:53
 */
@Slf4j
public class ProxyServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if (msg instanceof HttpRequest) {
            DefaultHttpRequest request = (DefaultHttpRequest) msg;
            String host = request.headers().get("host");
            String uri = request.uri().toString();
            log.info("host:{} uri:{}", host, uri);
            request.headers().forEach(e -> {
                log.info("header:{}=>{}", e.getKey(), e.getValue());
            });


//            String msgResp = "host:" + host + " uri:" + uri;

            String msgContent = HttpClientUtil.get("http://www.frdic.com/");
            log.info("content:{}", msgContent);

            msgContent = msgContent.replace("输入法", "InputMethod");

            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.copiedBuffer(msgContent, CharsetUtil.UTF_8));
            // 设置头信息
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            response.headers().set("MyHeader", "Netty-S");
            response.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            // 将html write到客户端
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

//            FullHttpResponse response = new DefaultFullHttpResponse(
//                    HTTP_1_1, OK, Unpooled.wrappedBuffer(content != null ? content
//                    .getBytes() : new byte[0]));
//            response.headers().set(CONTENT_TYPE, "image/png;charset=UTF-8");
//            response.headers().set(CONTENT_LENGTH,
//                    response.content().readableBytes());
//            response.headers().set(CONNECTION, HttpHeaderValues.KEEP_ALIVE);
//            ctx.write(response);
//            ctx.flush();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        log.error(cause.toString());
        ctx.close();
    }
}
