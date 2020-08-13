package com.qatix.base.lang.queue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/6 7:26 PM
 */
public class PriorityQueueIntExample {

    public static void main(String[] args) {
        PriorityQueue<Integer> pq1 =
                new PriorityQueue<>(Integer::compareTo);

        PriorityQueue<Integer> pq2 =
                new PriorityQueue<>(Comparator.reverseOrder());

        int[] data = new int[]{12,323,431,31,56,811,331};
        for(int i:data){
            pq1.add(i);
            pq2.add(i);
        }
        System.out.println(pq1.peek());
        System.out.println(pq2.peek());
    }
}
