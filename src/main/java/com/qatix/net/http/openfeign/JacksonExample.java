package com.qatix.net.http.openfeign;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.List;

public class JacksonExample {
    public static void main(String[] args) {
        GitHub github = Feign.builder()
//                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(GitHub.class, "https://api.github.com");
        List<GitHub.Contributor> contributors = github.contributors("OpenFeign", "feign");
        for (GitHub.Contributor contributor : contributors) {
            System.out.println(contributor.login + " (" + contributor.contributions + ")");
        }
    }
}
