package com.qatix.net.http.openfeign;

import com.qatix.base.spring.boot.MyService;
import feign.hystrix.HystrixFeign;

public class HystrixExample {
    public static void main(String[] args) {
        MyService api = HystrixFeign.builder().target(MyService.class, "https://myAppProd");
    }
}
