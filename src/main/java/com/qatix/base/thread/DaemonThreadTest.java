package com.qatix.base.thread;

import java.util.concurrent.TimeUnit;

/**
 * https://blog.csdn.net/wangming520liwei/article/details/79750924
 *
 * @Author: Logan.Tang
 * @Date: 2018/11/16 1:05 PM
 */
public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread mainThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread childThread = new Thread(new ClildThread());
                //如果daemon=true,父线程停止，子线程也停止
                //如果daemon=false,父线程停止，子线程依然运营
                childThread.setDaemon(false);
                childThread.start();
                System.out.println("I'm main thread...");
            }
        });
        mainThread.start();
    }
}

class ClildThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("I'm child thread..");
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}