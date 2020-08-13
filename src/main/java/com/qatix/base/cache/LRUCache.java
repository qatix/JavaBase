package com.qatix.base.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, Integer> data = null;
    private Map<Integer, Integer> lastUsed = null;
    private int capacity = 0;
    private int modIdx = 0;

    public LRUCache(int capacity) {
        data = new HashMap<>(capacity);
        lastUsed = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    public int get(int key) {
        int r = data.getOrDefault(key, -1);
        if (r > 0) {
            lastUsed.put(key, modIdx++);
        }
        return r;
    }

    public void put(int key, int value) {
        if (data.containsKey(key) || data.size() < capacity) {
            data.put(key, value);
            lastUsed.put(key, modIdx++);
        } else {
            //remove one
            int minKey = -1;
            long minVal = System.currentTimeMillis();
            for (Map.Entry<Integer, Integer> entry : lastUsed.entrySet()) {
                if (entry.getValue() < minVal) {
                    minKey = entry.getKey();
                    minVal = entry.getValue();
                }
            }
            data.remove(minKey);
            lastUsed.remove(minKey);

            data.put(key, value);
            lastUsed.put(key, modIdx++);
        }
    }
}
