package com.qatix.base.lang.unsafe;

import sun.misc.Unsafe;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * park方法用于Block current thread。在以下情况下返回
 * <p>
 * unpark方法最好不要在调用park前对当前线程调用unpark
 * <p>
 * <p>
 * /**
 * * Unblock the given thread blocked on <tt>park</tt>, or, if it is
 * * not blocked, cause the subsequent call to <tt>park</tt> not to
 * * block. Note: this operation is "unsafe" solely because the
 * * caller must somehow ensure that the thread has not been
 * * destroyed. Nothing special is usually required to ensure this
 * * when called from Java (in which there will ordinarily be a live
 * * reference to the thread) but this is not nearly-automatically
 * * so when calling from native code.
 * * @param thread the thread to unpark.
 * *
 * public native void unpark(Object thread);
 * <p>
 * **
 * *Unblock the given thread blocked on<tt>park</tt>,or,if it is
 * *not blocked,cause the subsequent call to<tt>park</tt>not to
 * *block.Note:this operation is"unsafe"solely because the
 * *caller must somehow ensure that the thread has not been
 * *destroyed.Nothing special is usually required to ensure this
 * *when called from Java(in which there will ordinarily be a live
 * *reference to the thread)but this is not nearly-automatically
 * *so when calling from native code.
 * *@param thread the thread to unpark.
 * *
 * public native void unpark(Object thread);
 */
public class ParkAndUnparkExample {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    //1 针对当前线程已经调用过unpark(多次调用unpark的效果和调用一次unpark的效果一样)
    private static void test1() {
        Unsafe unsafe = Util.getUnsafe();

        Thread currThread = Thread.currentThread();

        unsafe.unpark(currThread);
        unsafe.unpark(currThread);
        unsafe.unpark(currThread);

        unsafe.park(false, 0);

        System.out.println("SUCCESS!!!");
    }

    //在当前线程中断的时候或者调用unpark的时候
    private static void test2() {
        Unsafe unsafe = Util.getUnsafe();

        Thread currThread = Thread.currentThread();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                currThread.interrupt();
                //unsafe.unpark(currThread);
            } catch (Exception e) {
            }
        }).start();
        System.out.println(LocalDateTime.now());
        unsafe.park(false, 0);

        System.out.println("SUCCESS!!!");
        System.out.println(LocalDateTime.now());
    }

    //如果是相对时间也就是isAbsolute为false（注意这里后面的单位纳秒）到期的时候
    private static void test3() {
        Unsafe unsafe = Util.getUnsafe();
        //相对时间后面的参数单位是纳秒
        System.out.println(LocalDateTime.now());
        //相对时间
        unsafe.park(false, Duration.ofSeconds(3, 0).toNanos());

        System.out.println("SUCCESS!!!");
        System.out.println(LocalDateTime.now());
    }

    //如果是绝对时间也就是isAbsolute为true(注意后面的单位是毫秒)到期的时候
    private static void test4() {
        Unsafe unsafe = Util.getUnsafe();
        System.out.println(LocalDateTime.now());
        long time = System.currentTimeMillis() + 3000;
        //绝对时间后面的参数单位是毫秒
        unsafe.park(true, time);

        System.out.println("SUCCESS!!!");
        System.out.println(LocalDateTime.now());

    }
}
