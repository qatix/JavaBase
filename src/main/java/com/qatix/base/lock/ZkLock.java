package com.qatix.base.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 基于zk实现分布式锁
 * https://www.cnblogs.com/liuyang0/p/6800538.html
 */
public class ZkLock implements Watcher, Lock {
    private ZooKeeper zooKeeper;
    private String ROOT_LOCK = "/locks";
    private String lockName;
    private String WAIT_LOCK;
    private String CURRENT_LOCK;

    private CountDownLatch countDownLatch;
    private int sessionTimout = 30000;
    private List<Exception> exceptionList = new ArrayList<>();

    public ZkLock(String config, String lockName) {
        this.lockName = lockName;
        try {
            zooKeeper = new ZooKeeper(config, sessionTimout, this);
            Stat stat = zooKeeper.exists(ROOT_LOCK, false);
            if (stat == null) {
                zooKeeper.create(ROOT_LOCK, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        if (exceptionList.size() > 0) {
            throw new LockException(exceptionList.get(0));
        }
        try {
            if (this.tryLock()) {
                System.out.println(Thread.currentThread().getName() + " " + lockName + " get lock");
            } else {
                waitForLock(WAIT_LOCK, sessionTimout);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        this.lock();
    }

    @Override
    public boolean tryLock() {
        try {
            String splitStr = "_lock_";
            if (lockName.contains(splitStr)) {
                throw new LockException("lock name error");
            }

            CURRENT_LOCK = zooKeeper.create(ROOT_LOCK + "/" + lockName + splitStr, new byte[0],
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(CURRENT_LOCK + " created");
            List<String> subNodes = zooKeeper.getChildren(ROOT_LOCK, false);
            List<String> lockObjects = new ArrayList<>();
            for (String node : subNodes) {
                String tnode = node.split(splitStr)[0];
                if (tnode.equals(lockName)) {
                    lockObjects.add(node);
                }
            }
            Collections.sort(lockObjects);
            System.out.println(Thread.currentThread().getName() + " lock is " + CURRENT_LOCK);
            //若当前节点的是最小节点，则获得锁
            if (CURRENT_LOCK.equals(ROOT_LOCK + "/" + lockObjects.get(0))) {
                return true;
            }

            //如不是最小节点，则找到自己的前一个节点
            String prevNode = CURRENT_LOCK.substring(CURRENT_LOCK.lastIndexOf("/") + 1);
            WAIT_LOCK = lockObjects.get(Collections.binarySearch(lockObjects, prevNode) - 1);

        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        try {
            if (this.tryLock()) {
                return true;
            }
            return waitForLock(WAIT_LOCK, timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean waitForLock(String prev, long waitTime) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(ROOT_LOCK + "/" + prev, true);
        if (stat != null) {
            System.out.println(Thread.currentThread().getName() + " wait for lock [" + ROOT_LOCK + "/" + prev + "]");
            this.countDownLatch = new CountDownLatch(1);
            this.countDownLatch.await(waitTime, TimeUnit.MILLISECONDS);
            this.countDownLatch = null;
            System.out.println(Thread.currentThread().getName() + " wait and get lock");
        }
        return true;
    }

    @Override
    public void unlock() {
        try {
            System.out.println("release lock " + CURRENT_LOCK);
            zooKeeper.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (this.countDownLatch != null) {
            this.countDownLatch.countDown();
        }
    }

    public class LockException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public LockException(String message) {
            super(message);
        }

        public LockException(Throwable cause) {
            super(cause);
        }
    }
}
