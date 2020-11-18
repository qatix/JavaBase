package com.qatix.base.thread;

/**
 * java进程在收到关闭信号后，会执行所有绑定了shutdownHook的线程，确保这些绑定的线程都执行完了才真正关闭。因此，我们要释放资源就要在shutdownHook的线程内操作，然后在线程内等待其他释放资源的线程执行完成。
 *
 * 注意，所有绑定了shutdownHook的线程也是并行执行的，不是顺序执行。另外，用-9参数的kill不会等shutdownHook线程执行完就退出。
 */
public class ShutdownHook {
    public static void main(String[] args) {
        final Thread waitThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread begin");

                //等待获取许可
                try {
                    Thread.sleep(1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //输出thread over.true
                System.out.println("thread over." + Thread.currentThread().isInterrupted());

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        waitThread.start();
        //绑定钩子
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    waitThread.interrupt();
                    waitThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("shutdown success");
            }
        }));
    }
}
