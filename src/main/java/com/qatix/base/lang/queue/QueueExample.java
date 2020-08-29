package com.qatix.base.lang.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        queue.add(2);
        queue.add(5);
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }

    }
}
