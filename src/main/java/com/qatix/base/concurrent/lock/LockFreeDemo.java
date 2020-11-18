package com.qatix.base.concurrent.lock;

import java.util.stream.IntStream;

public class LockFreeDemo {
    public static void main(String[] args) {
        LockFreeQueue queue = new LockFreeQueue(5);
        IntStream.rangeClosed(1, 10).parallel().forEach(
                i -> {
                    if (i % 2 == 0) {
                        queue.add(i);
                    } else {
                        queue.poll();
                    }
                }
        );
        queue.print();
    }
}
