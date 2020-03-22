package com.qatix.base.http;


import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Xiaojun Tang
 * @date at 2019/7/16 11:10
 */

public class HttpClientUtil {

    /**
     * 最大连接数
     */

    private final static int MAX_TOTAL_CONNECTIONS = 1024;

    /**
     * 每个路由最大连接数
     */

    private final static int MAX_ROUTE_CONNECTIONS = 100;

    /**
     * 每个连接的路由最大连接数
     */

    private final static int MAX_PER_ROUTE = 20;

    /**
     * 连接超时时间
     */

    private final static int CONNECT_TIMEOUT = 3000;

    /**
     * 请求超时时间
     */

    private static final int REQUEST_TIMEOUT = 10 * 1000;

    /**
     * 读取超时时间
     */

    private final static int READ_TIMEOUT = 10 * 1000;

    /**
     * 最大重拾次数
     */

    private final static int MAX_RETRY = 3;
    private final static Object SYNC_LOCK = new Object();
    private static CloseableHttpClient httpClient = null;

    private static void config(HttpRequestBase httpRequestBase) {

// 设置Header等

        // httpRequestBase.setHeaders("User-Agent", "Mozilla/5.0");

        // 配置请求的超时设置

        RequestConfig requestConfig = RequestConfig.custom()

//连接超时时间

                .setConnectTimeout(CONNECT_TIMEOUT)

//请求超时时间

                .setConnectionRequestTimeout(REQUEST_TIMEOUT)

//响应超时时间

                .setSocketTimeout(READ_TIMEOUT).build();

        httpRequestBase.setConfig(requestConfig);

    }

    /**
     * 获取HttpClient对象
     */

    private static CloseableHttpClient getHttpClient(String url) {
        String hostname = url.split("/")[2];
        int port = 80;
        if (hostname.contains(":")) {
            String[] arr = hostname.split(":");
            hostname = arr[0];
            port = Integer.parseInt(arr[1]);
        }

        if (httpClient == null) {
            synchronized (SYNC_LOCK) {
                if (httpClient == null) {
                    httpClient = createHttpClient(hostname, port);
                }
            }
        }

        return httpClient;
    }

    /**
     * 创建HttpClient对象
     */

    private static CloseableHttpClient createHttpClient(String hostname, int port) {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory
                .getSocketFactory();

        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory
                .getSocketFactory();

        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory>create().register("http", plainsf)
                .register("https", sslsf).build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加
        cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        // 将每个路由基础的连接增加
        cm.setDefaultMaxPerRoute(MAX_PER_ROUTE);
        HttpHost httpHost = new HttpHost(hostname, port);

        // 将目标主机的最大连接数增加
        cm.setMaxPerRoute(new HttpRoute(httpHost), MAX_ROUTE_CONNECTIONS);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= MAX_RETRY) {// 如果已经重试了最大次数，就放弃
                    return false;
                }

                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }

                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }

                if (exception instanceof InterruptedIOException) {// 超时
                    return false;
                }

                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }

                if (exception instanceof SSLException) {// SSL握手异常
                    return false;
                }

                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                return !(request instanceof HttpEntityEnclosingRequest);
            }
        };

        return HttpClients.custom()
                .setConnectionManager(cm)
                .setRetryHandler(httpRequestRetryHandler).build();
    }

    private static void setPostParams(HttpPost httpPost,
                                      Map<String, Object> params) {

        List<NameValuePair> nvps = new ArrayList<>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, params.get(key).toString()));
        }

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
    }

    private static void setHeaders(HttpPost httpPost, Map<String, String> headersParams) {
        if (headersParams != null) {
            for (Map.Entry<String, String> entry : headersParams.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    public static String post(String url, Map<String, Object> params, Map<String, String> headersParams) {
        HttpPost httpPost = new HttpPost(url);
        config(httpPost);
        setPostParams(httpPost, params);
        setHeaders(httpPost, headersParams);
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient(url).execute(httpPost, HttpClientContext.create());

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String postJson(String url, String jsonString, Map<String, String> headersParams) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        config(httpPost);
        httpPost.setEntity(new StringEntity(jsonString));
        setHeaders(httpPost, headersParams);
        CloseableHttpResponse response = null;
        try {
            response = getHttpClient(url).execute(httpPost,
                    HttpClientContext.create());
            HttpEntity entity = response.getEntity();

            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String get(String url) {
        HttpGet httpget = new HttpGet(url);
        config(httpget);
        CloseableHttpResponse response = null;

        try {
            response = getHttpClient(url).execute(httpget, HttpClientContext.create());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            EntityUtils.consume(entity);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String url = "https://www.baidu.com";
        String content = HttpClientUtil.get(url);
        System.out.println("response:" + content);
    }
}
