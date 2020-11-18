package com.qatix.base.lang.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class List2Arr {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(22);
        list.add(444);
        list.add(33);
        Integer[] res= list.toArray(new Integer[0]);
        System.out.println(Arrays.toString(res));
        Collections.reverse(list);
        System.out.println(list);

        int[] res2 = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(res2));

        int[] arr2 = {4,5,8,1};
        Arrays.sort(arr2);
        list.addAll(Arrays.stream(arr2).boxed().collect(Collectors.toList()));
        System.out.println(list);

        List<Integer> list2 = Arrays.stream(arr2).boxed().collect(Collectors.toList());
        list2.add(9);
        System.out.println(list2);



    }
}
