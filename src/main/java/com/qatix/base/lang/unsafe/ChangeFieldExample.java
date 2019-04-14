package com.qatix.base.lang.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class ChangeFieldExample {

    public static void main(String[] args) throws NoSuchFieldException, InstantiationException {
        Unsafe unsafe = Util.getUnsafe();
        assert unsafe != null;
        User user = (User) unsafe.allocateInstance(User.class);
        user.setId(1);
        user.setName("zhang");
        user.setRole("admin");
        System.out.println(user);

        Field nameField = user.getClass().getDeclaredField("name");


        long nameOffset = unsafe.objectFieldOffset(nameField);
        System.out.println("offset:" + nameOffset);
        unsafe.putObject(user, nameOffset, "unsafe-name");

        System.out.println(user);
    }
}
