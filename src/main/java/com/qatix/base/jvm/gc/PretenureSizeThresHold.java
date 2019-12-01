package com.qatix.base.jvm.gc;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * java gc.MinorGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+PrintGCDetails
 */
public class PretenureSizeThresHold {
    public static final int _1MB = 1024 * 1024;

    public static void testPreteureSizeThresHold() {
        byte[] allocation;
        allocation = new byte[5 * _1MB];
    }

    public static void main(String[] args) {

        MemoryMXBean memoryMBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memoryMBean.getHeapMemoryUsage();
        System.out.println("初始化 Heap: " + (usage.getInit() / 1024 / 1024) + "mb");
        System.out.println("最大Heap: " + (usage.getMax() / 1024 / 1024) + "mb");
        System.out.println("已经使用Heap: " + (usage.getUsed() / 1024 / 1024) + "mb");
        System.out.println("Heap Memory Usage: " + memoryMBean.getHeapMemoryUsage());

        testPreteureSizeThresHold();
    }
}
