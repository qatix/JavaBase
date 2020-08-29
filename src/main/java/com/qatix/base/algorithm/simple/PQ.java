package com.qatix.base.algorithm.simple;

import java.util.PriorityQueue;

public class PQ {
    public static void main(String[] args) {
        int[] s = new int[]{5,6,7,1,8,10};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i:s){
            pq.add(i);
        }
        int r = 0;
        if(pq.size() == 1){
            r =  pq.peek();
        }
        while (pq.size() >= 2){
            int a = pq.poll();
            int b = pq.poll();
            int c = a+b;
            System.out.println(a+"+"+b+"="+c);
            r = r+c;
            pq.add(c);
        }
        System.out.println(r);
    }
}
