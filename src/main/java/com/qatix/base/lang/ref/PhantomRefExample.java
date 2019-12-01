package com.qatix.base.lang.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用主要用来跟踪对象被垃圾回收器回收的活动。虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列 （ReferenceQueue）联合使用。当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之 关联的引用队列中。
 * https://www.cnblogs.com/yw-ah/p/5830458.html
 *
 * @Author: Logan.Tang
 * @Date: 2018/11/16 1:27 PM
 */
public class PhantomRefExample {
    public static void main(String[] args) {
        ReferenceQueue queue = new ReferenceQueue<>();
        Object obj = new Object();
        PhantomReference<Object> pf = new PhantomReference<>(obj, queue);
        System.out.println(pf.get());
        obj = null;
        System.out.println(pf.get());//有时候会返回null,但我测试没发现
        System.out.println(pf.isEnqueued());
        System.out.println(queue);


        String str = new String("456");
        PhantomReference<String> pf2 = new PhantomReference<>(str, queue);
        System.out.println(pf2.get());
        str = null;
        System.out.println(pf2.get());
        System.out.println(pf2.isEnqueued());
        System.out.println(queue);
    }
}
