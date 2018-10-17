package com.qatix.base.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(1024);//有界队列
        queue.put("ele1");
        queue.put("ele2");
        System.out.println(queue.take());
        System.out.println(queue.take());

        BlockingQueue<String> queue2 = new LinkedBlockingQueue<String>(2);
        queue2.put("ele1");
        queue2.put("ele2");
        queue2.add("ele3");
//        queue2.put("ele3");
        System.out.println(queue2.take());
        System.out.println(queue2.take());
        System.out.println(queue2.take());

//        BlockingQueue<String> unbounded = new LinkedBlockingQueue<String>();//无界队列
//        BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);//有界队列
    }
}
