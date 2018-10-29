package com.qatix.base.lang.statiz;

/**
 * @Author: Logan.Tang
 * @Date: 2018/10/29 6:48 PM
 */
public class SClzTest {
    public static void main(String[] args) {
        System.out.println("start");
        //什么都不会执行
         SClz sClz1;

        System.out.println("--------");
         //static block code
        //Inner Clz constructed
        SClz sClz = new SClz("123");

        System.out.println("--------");
        //其它代码都不会执行
        System.out.println(SClz.ABC);

        System.out.println("--------");
        //static block code
        //Inner Clz constructed
        //InnerClz
        System.out.println(SClz.innerClz);

        System.out.println("end");
    }
}
