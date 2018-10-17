package com.qatix.base.concurrent.queue;

import java.util.ArrayDeque;

public class DequeTest {
    public static void main(String[] args) {
        ArrayDeque queue = new ArrayDeque<>();
        queue.offer("JAVA SE");
        queue.offer("JAVA EE");
        queue.offer("ORACLE 11G");
        queue.offer("NODE.JS");
        queue.add("TT");
//        queue.push("KC");
        System.out.println(queue);
        System.out.println(queue.peek());
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
