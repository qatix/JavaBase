package com.qatix.base.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport.park() 的实现原理是通过二元信号量做的阻塞，要注意的是，这个信号量最多只能加到1。
 * 我们也可以理解成获取释放许可证的场景。unpark()方法会释放一个许可证，park()方法则是获取许可证，
 * 如果当前没有许可证，则进入休眠状态，知道许可证被释放了才被唤醒。无论执行多少次unpark()方法，
 * 也最多只会有一个许可证。
 *
 * 对中断的处理
 * park方法不会抛出InterruptedException，但是它也会响应中断。
 * 当外部线程对阻塞线程调用interrupt方法时，park阻塞的线程也会立刻返回。
 */
public class ParkTest {
    public static void main(String[] args) throws InterruptedException {
        Thread parkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("park begin");

                //等待获取许可
                LockSupport.park();
                //输出thread over.true
                System.out.println("thread over:" + Thread.currentThread().isInterrupted());

            }
        });
        parkThread.start();

        Thread.sleep(2000);
        // 中断线程
//        parkThread.interrupt();

        //unpark
        LockSupport.unpark(parkThread);

        System.out.println("main over");
    }
}
