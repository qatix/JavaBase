package com.qatix.base.thread;

public class Test {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(1);

        for (int i=0;i<20;i++){
            myThreadPool.execute(new Task("task-"+i));
        }
    }
}

class Task implements Runnable{
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(this.toString() + " " + Thread.currentThread().getName() + " is executing");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.toString() + " " +  Thread.currentThread().getName() + " is done");
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}
