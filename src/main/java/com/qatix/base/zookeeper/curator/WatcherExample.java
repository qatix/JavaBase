package com.qatix.base.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WatcherExample {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();

        try {

            String monitorPath = "/pn/qatix";
            PathChildrenCache pathChildrenCache = new PathChildrenCache(client, monitorPath, true);
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {

                    System.out.println("catch children change");
                    System.out.println("update event type:" + event.getType() + ",path:" + event.getData().getPath() + ",data:" + new String(event.getData().getData(), CHARSET));

                    List<ChildData> childDataList = pathChildrenCache.getCurrentData();
                    if (childDataList != null && childDataList.size() > 0) {
                        System.out.println("path all children list:");
                        for (ChildData childData : childDataList) {
                            System.out.println("path:" + childData.getPath() + ",data:" + new String(childData.getData(), CHARSET));
                        }
                    }
                }
            });
            pathChildrenCache.start();
            TimeUnit.MINUTES.sleep(5);
            pathChildrenCache.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }

    }
}

