package com.qatix.base.lang.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MapPutExample {
    public static void main(String[] args) {

        Map<Integer, Integer> amap = new HashMap<>();
        int[] nums = new int[]{1, 2, 3, 4, 2, 3, 7};
        for (int num : nums) {
            amap.putIfAbsent(num, 0);
        }
    }
}
