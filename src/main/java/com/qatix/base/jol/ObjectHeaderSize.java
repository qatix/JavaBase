package com.qatix.base.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 *HotSpot虚拟机的对象头包括两部分信息
 * 第一部分用于存储对象自身的运行时数据， 如哈希码（HashCode）、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID、偏向时 间戳等。
 * 对象头的另外一部分是类型指针，即对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例。
 *
 * 64位的操作系统，所以 mark word 占 8个字节。JDK8 klass point 默认开启了指针压缩，
 * 所以是4个字节 , 不足8的倍数的，使用padding对齐填充，其目的是为了计算机高效寻址。
 */
public class ObjectHeaderSize {
    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());
        //16

        System.out.println();
        ClassLayout layout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(layout1.toPrintable());
        //16

        System.out.println();
        ClassLayout layout11 = ClassLayout.parseInstance(new int[1]);
        System.out.println(layout11.toPrintable());
        //16

        System.out.println();
        ClassLayout layout2 = ClassLayout.parseInstance(new ArtisanTest());
        System.out.println(layout2.toPrintable());
    }

    // -XX:+UseCompressedOops           默认开启的压缩所有指针
    // -XX:+UseCompressedClassPointers  默认开启的压缩对象头里的类型指针Klass Pointer
    // Oops : Ordinary Object Pointers
    public static class ArtisanTest {
        //8B mark word
        //4B Klass Pointer   如果关闭压缩-XX:-UseCompressedClassPointers或-XX:-UseCompressedOops，则占用8B
        int id;        //4B
        String name;   //4B  如果关闭压缩-XX:-UseCompressedOops，则占用8B
        byte b;        //1B
        Object o;      //4B  如果关闭压缩-XX:-UseCompressedOops，则占用8B
    }
}
