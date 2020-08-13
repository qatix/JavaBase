package com.qatix.base.jvm.lock;

public class SyncCodeBlock {
    public int i;

    public void syncTask() {
        synchronized (this) {
            i++;
        }
    }
}
