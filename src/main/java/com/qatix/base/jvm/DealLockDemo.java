package com.qatix.base.jvm;


public class DealLockDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new DeadLockclass(true));//建立一个线程
        Thread t2 = new Thread(new DeadLockclass(false));//建立另一个线程
        t1.start();//启动一个线程
        t2.start();//启动另一个线程
    }
}
class DeadLockclass implements Runnable {
    public boolean falg;// 控制线程
    DeadLockclass(boolean falg) {
        this.falg = falg;
    }

    @Override
    public void run() {
        /**
         * 如果falg的值为true则调用t1线程
         */
        if (falg) {
            while (true) {
                synchronized (Suo.o1) {
                    System.out.println("o1 " + Thread.currentThread().getName());
                    synchronized (Suo.o2) {
                        System.out.println("o2 " + Thread.currentThread().getName());
                    }
                }
            }
        }
        /**
         * 如果falg的值为false则调用t2线程
         */
        else {
            while (true) {
                synchronized (Suo.o2) {
                    System.out.println("o2 " + Thread.currentThread().getName());
                    synchronized (Suo.o1) {
                        System.out.println("o1 " + Thread.currentThread().getName());
                    }
                }
            }
        }
    }
}

class Suo {
    static Object o1 = new Object();
    static Object o2 = new Object();
}
/*
*
* Found one Java-level deadlock:
=============================
"Thread-1":
  waiting to lock monitor 0x00007f85ea806d68 (object 0x000000076b4860d0, a java.lang.Object),
  which is held by "Thread-0"
"Thread-0":
  waiting to lock monitor 0x00007f85ea8082b8 (object 0x000000076b4860e0, a java.lang.Object),
  which is held by "Thread-1"

Java stack information for the threads listed above:
===================================================
"Thread-1":
	at com.qatix.base.jvm.DeadLockclass.run(DealLockDemo.java:41)
	- waiting to lock <0x000000076b4860d0> (a java.lang.Object)
	- locked <0x000000076b4860e0> (a java.lang.Object)
	at java.lang.Thread.run(Thread.java:748)
"Thread-0":
	at com.qatix.base.jvm.DeadLockclass.run(DealLockDemo.java:28)
	- waiting to lock <0x000000076b4860e0> (a java.lang.Object)
	- locked <0x000000076b4860d0> (a java.lang.Object)
	at java.lang.Thread.run(Thread.java:748)

Found 1 deadlock.

* */
