package com.qatix.base.concurrent.queue;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * https://www.cnblogs.com/sunzhenchao/p/3515085.html
 *
 * @param <K>
 * @param <V>
 */
public class DelayedCacheTest<K, V> {
    public ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>();
    public DelayQueue<DelayedItem<K>> queue = new DelayQueue<DelayedItem<K>>();

    public DelayedCacheTest() {
        Thread t = new Thread() {
            @Override
            public void run() {
                daemonCheckOverdueKey();
            }
        };
        t.setDaemon(true);
        t.start();
    }

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        int cacheNumber = 10;
        int liveTime = 0;
        DelayedCacheTest<String, Integer> cache = new DelayedCacheTest<String, Integer>();
        for (int i = 0; i < cacheNumber; i++) {
            liveTime = random.nextInt(3000);
            cache.put(i + "", i, random.nextInt(liveTime));
            if (random.nextInt(cacheNumber) > 7) {
                liveTime = random.nextInt(3000);
                System.out.println(i + "  " + liveTime);
                cache.put(i + "", i, random.nextInt(liveTime));
            }
        }
        System.out.println(cache.map);

        Thread.sleep(3000);
        System.out.println();
    }

    public void put(K k, V v, long liveTime) {
        V oldV = map.put(k, v);
        DelayedItem<K> tempItem = new DelayedItem<K>(k, liveTime);
        if (oldV != null) {
            queue.remove(tempItem);//把原来的key移除
        }
        queue.put(tempItem);//加入新的key
    }

    public void daemonCheckOverdueKey() {
        while (true) {
            DelayedItem<K> delayedItem = queue.poll();
            if (delayedItem != null) {
                map.remove(delayedItem.getT());
                System.out.println(System.nanoTime() + " remove " + delayedItem.getT() + " from cache");
            }
            try {
                Thread.sleep(300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class DelayedItem<T> implements Delayed {
    private T t;
    private long liveTime;
    private long removeTime;

    public DelayedItem(T t, long liveTime) {
        this.t = t;
        this.liveTime = liveTime;
        this.removeTime = TimeUnit.NANOSECONDS.convert(liveTime, TimeUnit.NANOSECONDS) - System.nanoTime();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(removeTime - System.nanoTime(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == null) return 1;
        if (o == this) return 0;
        if (o instanceof DelayedItem) {
            DelayedItem<T> tmpItem = (DelayedItem<T>) o;
            if (liveTime > tmpItem.liveTime) {
                return 1;
            } else if (liveTime == tmpItem.liveTime) {
                return 0;
            } else {
                return -1;
            }
        }
        long diff = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return diff > 0 ? 1 : diff == 0 ? 0 : -1;
    }


    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public int hashCode() {
        return t.hashCode();
    }

}