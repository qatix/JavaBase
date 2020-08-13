package com.qatix.base.jvm.lock;

public class SyncMethod {
    public int i;
    public synchronized void syncTask(){
        i++;
    }

//    public static void main(String[] args) {
//        SyncMethod p = new SyncMethod();
//        p.syncTask();
//        System.out.println(p.i);
//    }
}
