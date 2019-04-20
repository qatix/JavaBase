package com.qatix.base.lang.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

final class Util {
    /**
     * 反射获取unsafe对象
     *
     * @return unsafe对象
     */
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
