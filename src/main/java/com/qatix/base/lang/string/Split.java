package com.qatix.base.lang.string;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/20 7:40 PM
 */
public class Split {
    public static void main(String[] args) {

        String str = "ac-c12-sd-1213";
        String[] sa1 = str.split("-");
        String[] sa2 = str.split("-", 2);
        System.out.println(Arrays.toString(sa1));
        System.out.println(Arrays.toString(sa2));

        System.out.println(Arrays.toString("A-".split("-", 2)));
        System.out.println(Arrays.toString("A,,".split(",")));
        System.out.println("A,,".split(",").length);
        System.out.println("A,,");
        System.out.println(Arrays.toString(StringUtils.split("A,,", ",", 3)));
    }
}
