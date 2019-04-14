package com.qatix.base.lang.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

final class Util {
    public static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
