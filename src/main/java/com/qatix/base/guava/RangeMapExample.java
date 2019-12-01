package com.qatix.base.guava;

import org.apache.curator.shaded.com.google.common.collect.Range;
import org.apache.curator.shaded.com.google.common.collect.RangeMap;
import org.apache.curator.shaded.com.google.common.collect.TreeRangeMap;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/30 2:24 PM
 */
public class RangeMapExample {
    public static void main(String[] args) {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10), "tang");
        rangeMap.put(Range.open(3, 6), "li");
        rangeMap.put(Range.open(10, 20), "huang");
        System.out.println(rangeMap.toString());

        rangeMap.remove(Range.closed(5, 11));
        System.out.println(rangeMap.toString());
    }
}
