package com.qatix.base.concurrent;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0,200000L);
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(task);
        try {
            long res = forkJoinTask.get();
            System.out.println("sum="+res);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.fillInStackTrace();
        }
    }
}

class CountTask extends RecursiveTask<Long> {
    private long start;
    private long end;
    private static final int THRESHOLD = 10000;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        System.out.println("compute("+ start + " - " + end +")" + "canCompute:" + canCompute);
        if (canCompute) {
            System.out.println("compute("+ start + " - " + end +")" + Thread.currentThread().getName());
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            long step = (end - start) / 100; //分成100个任务
            System.out.println("step="+step);
            ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if(lastOne > end){
                    lastOne = end;
                }
                CountTask subTask = new CountTask(pos,lastOne);
                subTasks.add(subTask);
                subTask.fork();
                pos += step+1;
            }

            for (CountTask task : subTasks){
                sum += task.join();
            }
        }
        return sum;
    }
}