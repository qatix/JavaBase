package com.qatix.base.lang.list;

import java.util.*;

public class ListOp {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(22);
        list.add(444);
        list.add(33);

        System.out.println(list);

        list.remove(list.size()-1);
        System.out.println(list);

        list.sort(Comparator.naturalOrder());
        System.out.println(list);

        list.sort(Comparator.reverseOrder());
        System.out.println(list);

        String str = list.toString();
        Set<String> set = new HashSet<>();
        set.add(str);
        System.out.println(set);
        System.out.println(set.contains(str));

        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
