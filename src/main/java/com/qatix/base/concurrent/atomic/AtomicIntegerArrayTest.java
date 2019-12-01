package com.qatix.base.concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {

    public static void main(String[] args) {

        AtomicIntegerArray ar1 = new AtomicIntegerArray(10);
        System.out.println(ar1);

        int[] ints = new int[10];
        ints[5] = 11;
        ints[7] = 33;
        AtomicIntegerArray ar2 = new AtomicIntegerArray(ints);
        System.out.println(ar2);

        int value = ar2.get(5);
        System.out.println(value);
        ar2.set(5, 99);
        System.out.println(ar2);

        boolean swaped = ar2.compareAndSet(5, 99, 56);
        System.out.println(swaped);
        System.out.println(ar2);

        int newValue = ar2.addAndGet(4, 23);
        System.out.println(newValue);

        newValue = ar2.incrementAndGet(4);
        System.out.println(newValue);

    }
}
