package com.qatix.base.lang.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @see https://my.oschina.net/gordonfor/blog/1922683
 */
public class CASExample {
    public static void main(String[] args) throws NoSuchFieldException, InstantiationException {
        Unsafe unsafe = Util.getUnsafe();
        assert unsafe != null;
        User user = (User) unsafe.allocateInstance(User.class);
        user.setId(1);
        user.setName("zhang");
        user.setRole("admin");
        user.setAge(30);
        System.out.println(user);

        Field nameField = user.getClass().getDeclaredField("name");
        long nameOffset = unsafe.objectFieldOffset(nameField);
//        nameField.setAccessible(true); //不必要
        System.out.println("offset:" + nameOffset);
        System.out.println("Before name:" + unsafe.getObject(user, nameOffset));
        unsafe.compareAndSwapObject(user, nameOffset, "zhang", "li");
        System.out.println(user);

        //下面不生效，id读出来是null
        //已解决：compareAndSwapInt不行，因为id是对象类型，改用compareAndSwapObject即可
        Field idField = user.getClass().getDeclaredField("id");
        long idOffset = unsafe.objectFieldOffset(idField);
        System.out.println("before Id:" + unsafe.getObject(unsafe, idOffset));
        unsafe.compareAndSwapObject(user, idOffset, 1, 200);
        System.out.println(user);


        user.setAge(1);
        try {
            Field ageField = user.getClass().getDeclaredField("age");
            long l = unsafe.objectFieldOffset(ageField);
            ageField.setAccessible(true);
            System.out.println("user age:" + user.getAge());
            System.out.println("unsafe age:" + unsafe.getObject(user, l));
            //比较并交换，比如age的值如果是所期望的值1，那么就替换为2，否则不做处理
            //如上compareAndSwapInt无效，并且如果我把age设置为int类型，33行会报错NullPointerException
            unsafe.compareAndSwapObject(user, l, 1, 2);
            System.out.println("user age is :" + user.getAge());

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
