package com.qatix.base.zookeeper;

import org.apache.zookeeper.AsyncCallback;

public class MyStringCallback implements AsyncCallback.StringCallback {
    @Override
    public void processResult(int i, String s, Object o, String s1) {
        System.out.println("Async create result,status:" + i + ",path:" + s + ",ctx:" + o + ",nodeName:" + s1);
    }
}
