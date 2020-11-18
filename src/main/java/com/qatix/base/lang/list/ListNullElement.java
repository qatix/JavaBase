package com.qatix.base.lang.list;

import java.util.ArrayList;
import java.util.List;

public class ListNullElement {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(null);
        list.add(1);
        list.add(null);
        list.add(null);
        System.out.println(list);
    }
}
