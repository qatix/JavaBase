package com.qatix.base.lang.ref;

import java.lang.ref.SoftReference;

/**
 * 如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。只要垃圾回收器没有回收它，该对象就可以被程序使用。软引用可用来实现内存敏感的高速缓存（下文给出示例）。
 *  * 软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
 *
 * @Author: Logan.Tang
 * @Date: 2018/11/16 1:21 PM
 */
public class SoftRefExample {
    public static void main(String[] args) {
        Object obj = new Object();
        SoftReference<Object> sf = new SoftReference<>(obj);
        System.out.println(sf.get());

        obj = null;

        System.out.println(sf.get());


        String str = new String("456");
        SoftReference<String> sf2 = new SoftReference<>(str);
        System.out.println(sf2.get());
        str = null;
        System.out.println(sf2.get());
    }
}
