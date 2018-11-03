package com.qatix.base.http;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/2 2:29 PM
 */
public class GetExample {
    public static void main(String[] args) {
        String url = "http://baidu.com";
        String result = HttpUtils.get(url);
        System.out.println(result);
    }
}
