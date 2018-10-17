package com.qatix.base.thread;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * https://blog.csdn.net/z55887/article/details/79060070
 */
public class MyThreadPool {

    private ArrayList<MyThread> threads;
    private ArrayBlockingQueue<Runnable> taskQueue;

    private int threadNum;
    private int workThreadNum;

    private final ReentrantLock lock = new ReentrantLock();

    public MyThreadPool(int threadNum) {
        this.threadNum = threadNum;
        threads = new ArrayList<>(threadNum);
        taskQueue = new ArrayBlockingQueue<>(threadNum*4);

        workThreadNum = 0;
    }


    public void execute(Runnable runnable){
        try {
            lock.lock();
            if(workThreadNum < this.threadNum){
                MyThread myThread = new MyThread(runnable,taskQueue);
                myThread.start();
                threads.add(myThread);
                workThreadNum++;
            }else{
                if(!taskQueue.offer(runnable)){
                    rejectTask(runnable);
                }
            }
        }finally {
            lock.unlock();
        }
    }

    private void rejectTask(Runnable task){
        System.out.println("task queue is full,task:" + task+" has been rejected");
    }
}
