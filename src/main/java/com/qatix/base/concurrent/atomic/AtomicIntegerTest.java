package com.qatix.base.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static void main(String[] args) {

        AtomicInteger a = new AtomicInteger();
        System.out.println(a.get());

        AtomicInteger ai = new AtomicInteger(123);
        int theValue = ai.get();
        System.out.println(theValue);

        ai.set(345);
        System.out.println(ai.get());

        AtomicInteger ai2 = new AtomicInteger(123);
        int expectedValue = 1235;
        int newValue = 234;
        boolean seted = ai2.compareAndSet(expectedValue, newValue);
        System.out.println("compare and set:(" + seted + ")" + ai2.get());


        AtomicInteger ai3 = new AtomicInteger();
        System.out.println("get and set :" + ai3.getAndAdd(10));
        System.out.println("now:" + ai3.get());
        System.out.println("add and get :" + ai3.addAndGet(11));

        AtomicInteger ai4 = new AtomicInteger();
        System.out.println("get and increment:" + ai4.getAndIncrement());
        System.out.println("now:" + ai4.get());
        System.out.println("increment and get:" + ai4.incrementAndGet());
    }
}
