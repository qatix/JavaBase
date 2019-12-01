package com.qatix.base.jvm.gc;

/**
 * java gc.MinorGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 */
public class MinorGC {
    public static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        System.out.println("testAllocation");
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];//出现一次minor gc
    }

    public static void testPreteureSizeThresHold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
        testAllocation();
//       System.gc();
    }
}
