package com.qatix.base.concurrent.queue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 双端队列，两边都可以插入，两端都可以取出
 */
public class LinkedBlockingDequeTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<String> deque = new LinkedBlockingDeque<String>();
        deque.addFirst("h-1");
        deque.addFirst("h-2");

        deque.addLast("t-1");
        deque.addLast("t-2");

        /**
         * h-2
         h-1
         t-1
         t-2
         */
//        System.out.println(deque.takeFirst());
//        System.out.println(deque.takeFirst());
//        System.out.println(deque.takeFirst());
//        System.out.println(deque.takeFirst());
//
        /**
         * 调用的是takeFirst()
         * h-2
         h-1
         t-1
         t-2
         */
        System.out.println(deque.take());
        System.out.println(deque.take());
        System.out.println(deque.take());
        System.out.println(deque.take());

    }
}
