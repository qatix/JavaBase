package com.qatix.base.lang.map;

import java.util.HashMap;
import java.util.Map;

public class MapSize {

    public static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(3);
        System.out.println(map.size());

        for(int i=1;i<10;i++){
            System.out.printf("cap of %d = %d\n",i,tableSizeFor(i));
        }
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
