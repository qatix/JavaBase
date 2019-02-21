package com.qatix.base.http;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.MediaType;

import java.io.IOException;

public class OkHttpExample {
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static void main(String[] args) throws IOException {
        testGet();
        testPost();
    }

    private static void testGet() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "http://baidu.com";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        String res = response.body().string();
        System.out.println(res);
    }

    private static void testPost() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "";
        String json = "";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        System.out.println(res);
    }
}
