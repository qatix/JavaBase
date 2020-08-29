package com.qatix.base.thread;

public class ThreadLocalExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new MyThread("Thread-01");
        thread.setName("app1");//此处设置无效，设置到当前线程
        thread.start();
        thread.join();
    }

    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }

        ThreadLocal<String> appNameHolder;

        public void setAppName(String name) {
            if (appNameHolder == null) {
                appNameHolder = new ThreadLocal<>();
            }
            appNameHolder.set(name);
        }

        public String getAppName() {
            if (appNameHolder == null) {
                return "None";
            }
            return appNameHolder.get();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " run");
            System.out.println("appName:" + getAppName());
        }
        //app1 run
        //appNameNone
    }
}
