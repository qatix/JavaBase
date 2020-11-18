package com.qatix.base.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLock {
    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public boolean lock() {
        while (!threadAtomicReference.compareAndSet(null, Thread.currentThread())) {
        }
        return true;
    }

    public void unlock() {
        threadAtomicReference.compareAndSet(Thread.currentThread(), null);
    }
}
