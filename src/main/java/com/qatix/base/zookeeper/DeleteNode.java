package com.qatix.base.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;

public class DeleteNode {

    private static void deleteNodeRecursive(ZooKeeper zooKeeper, String nodePath) throws KeeperException, InterruptedException {
        System.out.println("Node Path >> [" + nodePath + "]");

        if(zooKeeper.getChildren(nodePath,false).size() == 0){
            System.out.println("Delete Node Path >> [" + nodePath + "]");
            zooKeeper.delete(nodePath,-1);
        }else{
            List<String> children = zooKeeper.getChildren(nodePath,true);
            for (String child:children){
                deleteNodeRecursive(zooKeeper,nodePath+"/"+child);
            }
            zooKeeper.delete(nodePath,-1);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("process event:" + watchedEvent.getType());
            }
        });

        zk.create("/zkt","111".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.create("/zkt/a","222".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.create("/zkt/a/b1","333".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.create("/zkt/a/b2","333".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zk.create("/zkt/a/b3","333".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

        String node = "/zkt";
        deleteNodeRecursive(zk,node);
        zk.close();
    }
}
