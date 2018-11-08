package com.qatix.base.lang.other;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/4 10:40 AM
 */
public class PrimitiveParameters
{
    public static void main(String[] args)
    {	go();
    }

    public static void go()
    {	int x = 3;
        int y = 2;
        System.out.println("In method go. x: " + x + " y: " + y); // 3,2
        falseSwap(x,y);
        System.out.println("in method go. x: " + x + " y: " + y); // 3,2
        moreParameters(x,y);
        System.out.println("in method go. x: " + x + " y: " + y); // 3,2
    }

    public static void falseSwap(int x, int y)
    {	System.out.println("in method falseSwap. x: " + x + " y: " + y); //3,2
        int temp = x;
        x = y;
        y = temp;
        System.out.println("in method falseSwap. x: " + x + " y: " + y);//2,3
    }

    public static void moreParameters(int a, int b)
    {	System.out.println("in method moreParameters. a: " + a + " b: " + b); //3,2
        a = a * b;
        b = 12;
        System.out.println("in method moreParameters. a: " + a + " b: " + b); //6,12
        falseSwap(b,a);
        System.out.println("in method moreParameters. a: " + a + " b: " + b);//6,12
    }
}