package com.qatix.net.http.openfeign;

import com.squareup.okhttp.OkHttpClient;
import feign.Client;
import feign.Feign;

public class OkHttpExample {
    public static void main(String[] args) {
        GitHub github = Feign.builder()
                .client((Client) new OkHttpClient())
                .target(GitHub.class, "https://api.github.com");
    }
}
