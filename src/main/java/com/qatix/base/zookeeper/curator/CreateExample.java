package com.qatix.base.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class CreateExample {
    public static void main(String[] args) throws Exception {

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("d88:12181", retryPolicy);
        client.start();

        //create
        String res = client.create().orSetData().forPath("/ca", "curator".getBytes());
        System.out.println("create res:" + res);

        byte[] data = client.getData().forPath("/ca");
        System.out.println("get res:" + new String(data));

//        res = client.create().forPath("/ca/path","curator".getBytes());
//        System.out.println("create res:"+res);

        Stat stat = client.checkExists().forPath("/pn/qatix");
        System.out.println(stat);


        List<String> children = client.getChildren().forPath("/pn/qatix");
        for (String str : children) {
            System.out.println("c:" + str);
        }

        String path = "/qatix/test/l1/l2/l3";
        Stat stat1 = client.checkExists().forPath(path);
        if (stat1 == null) {
            System.out.println("stat1 === null");
        } else {
            System.out.println("stat1 != null");
        }

        path = "/qatix/no_exsit/p/c/a";
        res = client.create().creatingParentsIfNeeded().forPath(path,"create-parent-test".getBytes());
        System.out.println(res);

        path = "/qatix/no_exsit/p/c/b";
        res = client.create().withMode(CreateMode.EPHEMERAL).forPath(path,"create-parent-test".getBytes());
        System.out.println(res);

        path = "/qatix/no_exsit/p/c/c";
        res = client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path,"create-parent-test".getBytes());
        System.out.println(res);

        path = "/qatix/no_exsit/p/c/d";
        res = client.create().withMode(CreateMode.PERSISTENT).forPath(path,"create-parent-test".getBytes());
        System.out.println(res);

    }
}
