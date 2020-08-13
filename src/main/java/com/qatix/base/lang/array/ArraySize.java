package com.qatix.base.lang.array;

public class ArraySize {
    public static void main(String[] args) {
        int[] arr = new int[0];
        System.out.println(arr.length);
        System.out.println(arr == null); //false

        int[] arr2 = new int[3];
        System.out.println(arr2.length);
        System.out.println(arr2 == null); //false

        int[] arr3 = null;
        System.out.println(arr3 == null);//true
    }
}
