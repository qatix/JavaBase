package com.qatix.base.jvm.oom;

/**
 * java oom.VMStackOOM -Xss=2M
 * 系统卡死，错误应该是
 * java.lang.OutOfMemoryError:unable to create new native thread
 */
public class VMStackOOM {
    public static void main(String[] args) {
        VMStackOOM vmStackOOM = new VMStackOOM();
        vmStackOOM.stackLeakThread();
    }

    private void dontStop() {
        String threadName = Thread.currentThread().getName();
        int counter = 0;
        while (true) {
            counter++;
//            System.out.printf("%s counter:%d%n",threadName,counter);
        }
    }

    private void stackLeakThread() {
        while (true) {
            Thread thread = new Thread(this::dontStop);
            thread.start();
        }
    }
}
