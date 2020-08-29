package com.qatix.base.lang.list;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache<K, V> {
    private static int MAX_LENGTH = 1 << 20;  //最大长度
    private LinkedHashMap<K, V> map;
    private ReadWriteLock lock = new ReentrantReadWriteLock(); //读写锁

    public LRUCache(int initLength) {
        this(initLength, MAX_LENGTH);
    }

    public LRUCache(int initLength, int maxLength) {
        MAX_LENGTH = maxLength;
        map = new LinkedHashMap<K, V>(initLength, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > MAX_LENGTH;
            }
        };
    }

    /**
     * 添加项
     *
     * @param key  键
     * @param val 值
     */
    public void put(K key, V val) {
        lock.writeLock().lock();
        map.put(key, val);
        lock.writeLock().unlock();
    }

    /**
     * 获取值,使用前请判断是否存在item
     *
     * @param key 键
     * @return value 值
     */
    public V get(K key) {
        lock.readLock().lock();
        V value = map.get(key);
        lock.readLock().unlock();
        return value;
    }

    /**
     * 是否存在
     *
     * @param key 键
     * @return 是否存在
     */
    public boolean containsKey(K key) {
        lock.readLock().lock();
        boolean isContainer = map.containsKey(key);
        lock.readLock().unlock();
        return isContainer;
    }

    /**
     * 删除key
     *
     * @param key 键
     */
    public void remove(K key) {
        lock.writeLock().lock();
        map.remove(key);
        lock.writeLock().unlock();
    }
}
