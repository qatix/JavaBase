package com.qatix.base.algorithm.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class AllOne {
    Map<String, Item> data = null;
    PriorityQueue<Item> pq1 = null;
    PriorityQueue<Item> pq2 = null;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        data = new HashMap<>();
        pq1 = new PriorityQueue<>((o1, o2) -> o1.getCount() > o2.getCount() ? 1 : -1);
        pq2 = new PriorityQueue<>((o1, o2) -> o1.getCount() > o2.getCount() ? -1 : 1);
    }

    private static class Item {
        int count;
        String key;

        public Item(String key, int count) {
            this.key = key;
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public String getKey() {
            return key;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        Item c = data.get(key);
        if (c == null) {
            c = new Item(key, 1);
        } else {
            c.count++;
        }
        data.put(key, c);
        pq1.add(c);
        pq2.add(c);
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        Item c = data.get(key);
        if (c == null) {
            return;
        }
        c.count--;
        if (c.count == 0) {
            data.remove(key);
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        for (Item item : pq2) {
            System.out.println("max-item:" + item.key + "|" + item.getCount());
            if (data.containsKey(item.getKey())) {
                return item.getKey();
            }
        }
        return "";
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        for (Item item : pq1) {
            System.out.println("min-item:" + item.key + "|" + item.getCount());
            if (data.containsKey(item.getKey())) {
                return item.getKey();
            }
        }
        return "";
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        System.out.println("max:" + allOne.getMaxKey());
        System.out.println("min:" + allOne.getMinKey());
    }
}