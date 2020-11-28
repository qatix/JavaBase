package com.qatix.netty.staticproxy;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
public class ProxyHandler extends ChannelInboundHandlerAdapter {

    private RedisUtil redisUtil = new RedisUtil();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if (msg instanceof HttpRequest) {
            DefaultHttpRequest request = (DefaultHttpRequest) msg;
            String uri = request.uri();
            System.out.println("proxy-uri:" + uri);
            if ("/favicon.ico".equals(uri)) {
                return;
            }
            log.info(String.valueOf(LocalDateTime.now()));
            Jedis jedis = redisUtil.getJedis();
            String s = jedis.get(uri);
            if (s == null || s.length() == 0 || true) {
                System.out.println("get from origin server");
                try {
                    URL url = new URL("https://www.cnblogs.com" + uri);
                    log.info(url.toString());
                    URLConnection urlConnection = url.openConnection();
                    HttpURLConnection connection = (HttpURLConnection) urlConnection;
                    System.out.println("origin-method:" + request.method().name());
                    connection.setRequestMethod(request.method().name());
                    connection.setReadTimeout(3000);
                    //连接
                    connection.connect();
                    //得到响应码
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                                (connection.getInputStream(), StandardCharsets.UTF_8));
                        StringBuilder bs = new StringBuilder();
                        String l;
                        while ((l = bufferedReader.readLine()) != null) {
                            bs.append(l).append("\n");
                        }
                        s = bs.toString();
                    }

                    //do some extra work
                    if (s != null) {
                        s = s.replace("专区", "专区替换");
                    }
                    // extra work done

                    jedis.set(uri, s);
                    connection.disconnect();
                } catch (Exception e) {
                    log.error("", e);
                    return;
                }
            } else {
                System.out.println("get from cache");
            }
            jedis.close();
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HTTP_1_1, OK, Unpooled.wrappedBuffer(s != null ? s
                    .getBytes() : new byte[0]));
            response.headers().set(HttpHeaders.CONTENT_TYPE, "text/html");
            response.headers().set(HttpHeaders.CONTENT_LENGTH,
                    response.content().readableBytes());
            response.headers().set(HttpHeaders.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            ctx.write(response);
            ctx.flush();
        } else {
            //这里必须加抛出异常，要不然ab测试的时候一直卡住不动，暂未解决
            throw new Exception();
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }
}