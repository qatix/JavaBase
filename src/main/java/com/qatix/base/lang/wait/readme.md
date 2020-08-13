
在Java的Object类中有三个final的方法允许线程之间进行资源对象锁的通信，他们分别是： wait(), notify() and notifyAll()。

调用这些方法的当前线程必须拥有此对象监视器，否则将会报java.lang.IllegalMonitorStateException exception异常。

# wait
```
Object的wait方法有三个重载方法，其中一个方法wait() 是无限期(一直)等待，直到其它线程调用notify或notifyAll方法唤醒当前的线程；另外两个方法wait(long timeout) 和wait(long timeout, int nanos)允许传入 当前线程在被唤醒之前需要等待的时间，timeout为毫秒数，nanos为纳秒数。
```

# notify
```
notify方法只唤醒一个等待（对象的）线程并使该线程开始执行。所以如果有多个线程等待一个对象，这个方法只会唤醒其中一个线程，选择哪个线程取决于操作系统对多线程管理的实现。
```

# notifyAll
```
notifyAll 会唤醒所有等待(对象的)线程，尽管哪一个线程将会第一个处理取决于操作系统的实现。

这些方法可以使用于“生产者-消费者”问题，消费者是在队列中等待对象的线程，生产者是在队列中释放对象并通知其他线程的线程。
```