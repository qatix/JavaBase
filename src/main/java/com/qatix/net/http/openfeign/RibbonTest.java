package com.qatix.net.http.openfeign;

import feign.Feign;

public class RibbonTest {
    public static void main(String[] args) {
        MyService api = Feign.builder()
//                .client(RibbonClient.create())
                .target(MyService.class, "https://myAppProd");
    }
}
