package com.qatix.base.guava.ordering;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/30 11:10 AM
 */
public class MinMaxExample {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a11");
        list.add("n888");
        list.add("4121");
        list.add("9000");
        list.add("c9");

        Ordering<String> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<String, Comparable>() {
            @Nullable
            @Override
            public Comparable apply(@Nullable String input) {
                return input;
            }
        });

        System.out.println("min:" + ordering.min(list));
        System.out.println("max:" + ordering.max(list));

        System.out.println("before sort:");
        System.out.println(list.toString());

        List<String> res = ordering.sortedCopy(list);
        System.out.println("after sort:");
        System.out.println(res.toString());

        System.out.println("is-ordered-before:" + ordering.isOrdered(list));
        System.out.println("is-ordered-after:" + ordering.isOrdered(res));

        List<String> greatK3 = ordering.greatestOf(list, 3);
        System.out.println("greatest k3:");
        System.out.println(greatK3.toString());
    }
}
