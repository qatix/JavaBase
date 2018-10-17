package com.qatix.base.thread;

import java.util.concurrent.ArrayBlockingQueue;

public class MyThread  extends Thread {

    private Runnable task;
    private ArrayBlockingQueue<Runnable> queue;

    public MyThread(Runnable task, ArrayBlockingQueue queue) {
        this.task = task;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            if(task != null){
                task.run();
                task = null;
            }else{
                task = this.queue.poll();
                if(task != null){
                    System.out.println("get task from queue:" + task);
                    task.run();
                    task = null;
                }
            }

        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        System.out.println(Thread.currentThread().getName() + " interrupt");
    }

    @Override
    public boolean isInterrupted() {
        System.out.println(Thread.currentThread().getName() + " isInterrupted:" + super.isInterrupted());
        return super.isInterrupted();
    }
}
