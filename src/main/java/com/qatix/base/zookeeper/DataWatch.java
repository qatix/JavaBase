package com.qatix.base.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class DataWatch implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("process:" + watchedEvent.toString());
        System.out.println("state:" + watchedEvent.getState());

        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        int timeOut = 5000;//5s
        ZooKeeper zk = new ZooKeeper("localhost:2181", timeOut, new DataWatch());
        countDownLatch.await();

        String ephemeralPath = zk.create("/zk-data", "111".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Sync create ephemeral node succeed:" + ephemeralPath);

        Thread.sleep(2000);
        zk.setData("/zk-data", "443".getBytes(), -1);

        Thread.sleep(300000);//等待结果
    }


}
