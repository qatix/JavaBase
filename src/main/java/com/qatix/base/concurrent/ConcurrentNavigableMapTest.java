package com.qatix.base.concurrent;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentNavigableMapTest {

    public static void main(String[] args) {
        ConcurrentNavigableMap map = new ConcurrentSkipListMap();
        map.put("1","one");
        map.put("2","two");
        map.put("3","three");
        map.put("4","four");
        map.put("5","five");

        ConcurrentNavigableMap headMap = map.headMap("2");
        System.out.println(headMap);

        ConcurrentNavigableMap tailMap = map.tailMap("3");
        System.out.println(tailMap);

        ConcurrentNavigableMap subMap = map.subMap("3","5");
        System.out.println(subMap);
        /**
         * {1=one}
         {3=three, 4=four, 5=five}
         {3=three, 4=four}
         */
    }
}
