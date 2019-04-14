package com.qatix.base.lang.unsafe;

import sun.misc.Unsafe;

/**
 * 可像C++一样操作内存
 */
public class MemoryExample {
    public static void main(String[] args) {
        Unsafe unsafe = Util.getUnsafe();
        assert unsafe != null;

        // allocate 8bytes memory
        long address = unsafe.allocateMemory(8L);

        unsafe.setMemory(address, 8L, (byte) 0);

        System.out.println("get data:" + unsafe.getInt(address));

        //set 0-3 4byte
        unsafe.putInt(address, 111);

        //set 4-7 4byte
        unsafe.putInt(address + 4, 222);

        System.out.println("first 4bytes:" + unsafe.getInt(address));
        System.out.println("last 4bytes:" + unsafe.getInt(address + 4));

        unsafe.putLong(address, 123456789012345L);
        System.out.println("get long:" + unsafe.getLong(address));
    }
}
