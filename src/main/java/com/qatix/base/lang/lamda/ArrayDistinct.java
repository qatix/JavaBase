package com.qatix.base.lang.lamda;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/19 6:27 PM
 */
public class ArrayDistinct {

    public static void main(String[] args) {

        List<App> apps = new ArrayList<>();
        apps.add(new App("Facebook", "Xiaoxi", 333));
        apps.add(new App("Facebook", "Xiaoxi", 333));
        apps.add(new App("Twitter", "OPPO", 333));
        apps.add(new App("Twitter", "HUAWEI", 111));

        List<String> channels = apps.stream().map(App::getChannel).distinct().collect(Collectors.toList());
        System.out.println(channels.toString());
    }

    @Data
    @AllArgsConstructor
    private static class App {
        String name;
        String channel;
        int viewCount;
    }
}
