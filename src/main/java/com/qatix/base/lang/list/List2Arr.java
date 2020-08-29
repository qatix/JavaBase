package com.qatix.base.lang.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class List2Arr {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(22);
        list.add(444);
        list.add(33);
        Integer[] res= list.toArray(new Integer[0]);
        System.out.println(Arrays.toString(res));

        int[] res2 = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(res2));
    }
}
