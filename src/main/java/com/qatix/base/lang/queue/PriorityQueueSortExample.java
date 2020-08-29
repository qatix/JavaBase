package com.qatix.base.lang.queue;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/6 7:26 PM
 */
public class PriorityQueueSortExample {

    public static void main(String[] args) {
        PriorityQueue<Item> priorityQueue =
                new PriorityQueue<>();
        while (priorityQueue.size() < 10) {
            priorityQueue.add(new Item(new Random().nextInt(991)));
        }
        System.out.println(priorityQueue.toString());
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }
    }

    public static class Item implements Comparable<Item> {
        private int score;

        public Item(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(Item o) {
            return o.score - this.score;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "score=" + score +
                    '}';
        }
    }
}
