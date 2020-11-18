package com.qatix.base.lang.array;

import java.util.Arrays;

public class ArrayInit {
    public static void main(String[] args) {
        int[] s= new int[10];
        Arrays.fill(s,-1);
        System.out.println(Arrays.toString(s));
    }
}
