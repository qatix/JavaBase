package com.qatix.base.lang.ref;

import java.lang.ref.WeakReference;

/**
 * 弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
 * 弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
 *
 * @Author: Logan.Tang
 * @Date: 2018/11/16 1:24 PM
 */
public class WeakRefExample {
    public static void main(String[] args) {
        Object obj = new Object();
        WeakReference<Object> wf = new WeakReference<>(obj);
        System.out.println(wf.get());
        obj = null;
        System.out.println(wf.get());//有时候会返回null,但我测试没发现
        System.out.println(wf.isEnqueued());


        String str = new String("456");
        WeakReference<String> wf2 = new WeakReference<>(str);
        System.out.println(wf2.get());
        str = null;
        System.out.println(wf2.get());
        System.out.println(wf2.isEnqueued());
    }
}
