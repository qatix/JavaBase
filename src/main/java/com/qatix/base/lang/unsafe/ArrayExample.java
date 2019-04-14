package com.qatix.base.lang.unsafe;

import sun.misc.Unsafe;

import java.util.Arrays;

/**
 * 不可变数组可通过unsafe进行修改
 */
public class ArrayExample {
    public static void main(String[] args) {
        Unsafe unsafe = Util.getUnsafe();
        assert unsafe != null;

        //不可变数组
        String[] arr = new String[]{"a11", "b11", "k65", "999"};
        System.out.println(Arrays.toString(arr));

        long baseOffset = unsafe.arrayBaseOffset(String[].class);
        System.out.println("baseOffset:" + baseOffset);

        long scale = unsafe.arrayIndexScale(String[].class);
        System.out.println("scale:" + scale);

        System.out.println("first element :" + unsafe.getObject(arr, baseOffset));
        System.out.println("third element :" + unsafe.getObject(arr, baseOffset + scale * 2));

        //put
        unsafe.putObject(arr, baseOffset + scale * 2, "3333");
        System.out.println(Arrays.toString(arr));

        //out of bound，不会报错，也不会有生效
        //putObject 是一个native方法，看不到实现
        unsafe.putObject(arr, baseOffset + scale * 4, "5555");
        System.out.println(Arrays.toString(arr));
    }
}
