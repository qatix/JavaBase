package com.qatix.base.lang.queue;

import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/6 7:26 PM
 */
public class PriorityQueueExample {

    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue =
                new PriorityQueue<>();
        priorityQueue.add("Abc");
        priorityQueue.add("Abd");
        priorityQueue.add("Bde");
        priorityQueue.add("Xyz");

        System.out.println("Head value using peek function:" + priorityQueue.peek());

        System.out.println("The queue elements:");
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        priorityQueue.remove("Bde");
        System.out.println(priorityQueue.toString());

        // Check if an element is present using contains()
        boolean b = priorityQueue.contains("C");
        System.out.println ( "Priority queue contains C " +
                "or not?: " + b);

        // Getting objects from the queue using toArray()
        // in an array and print the array
        Object[] arr = priorityQueue.toArray();
        System.out.println ( "Value in array: ");
        for (int i = 0; i<arr.length; i++) {
            System.out.println("Value: " + arr[i].toString());
        }
    }
}
