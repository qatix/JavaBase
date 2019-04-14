package com.qatix.base.lang.unsafe;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;

public class ThreadExample {
    public static void main(String[] args) {
        Unsafe unsafe = Util.getUnsafe();
        assert unsafe != null;

        Thread parkThread = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            //nano seconds，相对时间
            unsafe.park(false, 3000000000L);

            //milli seconds,绝对时间
//                unsafe.park(true,System.currentTimeMillis()+3000);

            System.out.println("main thread end,cost:" + (System.currentTimeMillis() - startTime) + "ms");
        });
        parkThread.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //注释掉下一行后，线程3秒数后进行输出,否则在1秒后输出，原因是1秒后主线程直接unpark了，子线程内的park直接通过
        unsafe.unpark(parkThread);
    }
}
