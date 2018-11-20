package com.qatix.base.jvm.monitor;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/15 9:39 AM
 */
public class MemInfo {
    public static String getMemUsage() {
        long free = java.lang.Runtime.getRuntime().freeMemory();
        long total = java.lang.Runtime.getRuntime().totalMemory();
        StringBuffer buf = new StringBuffer();
        buf.append("[Mem: used ").append((total-free)>>20)
                .append("M free ").append(free>>20)
                .append("M total ").append(total>>20).append("M]");
        return buf.toString();
    }

    public static void main(String[] args) {
        System.out.println(getMemUsage());
    }
}
