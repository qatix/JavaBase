package com.qatix.base.lang.unsafe;

import sun.misc.Unsafe;

public class ConstantExample {
    public static void main(String[] args) {
        Unsafe unsafe = Util.getUnsafe();
        assert unsafe != null;

        System.out.println("address size:" + unsafe.addressSize());
        System.out.println("page size:" + unsafe.pageSize());
        System.out.println("unsafe array in base  offset:" + Unsafe.ARRAY_INT_BASE_OFFSET);
    }
}
