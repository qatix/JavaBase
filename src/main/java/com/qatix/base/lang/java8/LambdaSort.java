package com.qatix.base.lang.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/1 9:56 AM
 */
public class LambdaSort {
    public static void main(String[] args) {

        List<String> names = Arrays.asList(
                "Jim",
                "Abc",
                "Zkd",
                "Swam"
        );
        System.out.println(names);
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
//        Collections.sort(names, (o1, o2) -> o1.compareTo(o2));

        System.out.println(names);
    }
}
