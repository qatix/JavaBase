package com.qatix.base.lang.sort;

import java.util.Arrays;

public class IntSort {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3, 4, 11, 1, 5, 7, 2};
        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr, (o1, o2) -> {
            if (o1 > o2) {
                return -1;
            } else if (o1 < o2) {
                return 1;
            }
            return 0;
        });
        System.out.println(Arrays.toString(arr));

    }
}
