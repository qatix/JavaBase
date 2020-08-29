package com.qatix.base.lang.math;

public class Log {
    public static void main(String[] args) {
        double t = Math.log(17);

        System.out.println(t);
        System.out.println(Math.log1p(8));


        System.out.println(getCeilLog(1));
        System.out.println(getCeilLog(2));
        System.out.println(getCeilLog(3));
        System.out.println(getCeilLog(7));

        System.out.println(getLog2(1));
        System.out.println(getLog2(2));
        System.out.println(getLog2(3));
        System.out.println(getLog2(7));
    }

    private static int getCeilLog(int k) {
        int t = 1;
        int i = 1;
        while (t < k) {
            t = t + 1 << i;
            i++;
        }
        return i;
    }

    private static double getLog2(int k) {
        return Math.log10(k) / Math.log10(2.0);
    }
}
