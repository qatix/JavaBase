package com.qatix.base.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class CreateNode implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("process:" + watchedEvent.toString());
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        int timeOut = 5000;//5s
        ZooKeeper zooKeeper = new ZooKeeper("localhost:2181",timeOut,new CreateNode());
        countDownLatch.await();


        String ephemeralPath = zooKeeper.create("/zk-test-ephermeral-","111".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Sync create ephemeral node succeed:" + ephemeralPath);

        String sequentialPath = zooKeeper.create("/zk-test-sequential-","222".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Sync create sequential node succeed:" + sequentialPath);

        zooKeeper.create("/zk-test-ephermeral-async-","333".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL,new MyStringCallback(),"Params1");

        zooKeeper.create("/zk-test-sequential-async-","444".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL,new MyStringCallback(),"Params2");


        String persitentPath1 = zooKeeper.create("/pnode","111".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("Sync create persitent node succeed:" + persitentPath1);

        String persitentPath2 = zooKeeper.create("/pnode/persitent-sync","111".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("Sync create persitent node succeed:" + persitentPath2);

        String sequentialPath2 = zooKeeper.create("/pnode/sequential-sync","222".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println("Sync create sequential node succeed:" + sequentialPath2);

        zooKeeper.create("/pnode/persitent-async","333".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT,new MyStringCallback(),"Params1");

        zooKeeper.create("/pnode/sequential-async","444".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL,new MyStringCallback(),"Params2");

        Thread.sleep(300000);//等待结果
    }


}
