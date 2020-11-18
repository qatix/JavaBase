package com.qatix.base.lang.map;

import java.util.*;

public class SetExample {
    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        int[] nums = new int[]{1, 2, 3, 4, 2, 3, 7};
        for (int num : nums) {
            set.add(num);
        }
        int[] arr =  set.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(arr));
    }
}
