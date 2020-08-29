# 深入理解Linux底层epoll的实现原理
首先我们观察下Linux底层epoll的3个实现函数：
```
int epoll_create(int size);
int epoll_ctl(int epfd, int op, int fd, struct epoll_event *event);
int epoll_wait(int epfd, struct epoll_event *events,int maxevents, int timeout);
```

epoll_create：创建一个epoll对象。参数size是内核保证能处理最大的文件句柄数，在socket编程里面就是处理的最大连接数。返回的int代表当前的句柄指针，当然创建一个epoll对象的时候，也会相应的消耗一个fd，所以在使用完成的时候，一定要关闭，不然会耗费大量的文件句柄资源。

epoll_ctl：可以操作上面建立的epoll，例如，将刚建立的socket加入到epoll中让其监控，或者把 epoll正在监控的某个socket句柄移出epoll，不再监控它等等。其中epfd，就是创建的文件句柄指针，op是要做的操作，例如删除，更新等，event 就是我们需要监控的事件。

epoll_wait：在调用时，在给定的timeout时间内，当在监控的所有句柄中有事件发生时，就返回用户态的进程。

```
epoll的高效就在于，当我们调用epoll_ctl往里塞入百万个句柄时，epoll_wait仍然可以飞快的返回，
并有效的将发生事件的句柄发送给用户。这是由于我们在调用epoll_create时，
内核除了帮我们在epoll文件系统里建了个file结点，在内核cache里建了个红黑树用于存储以后epoll_ctl传来的socket外，
还会再建立一个list链表，用于存储准备就绪的事件，当epoll_wait调用时，仅仅观察这个list链表里有没有数据即可。
有数据就返回，没有数据就sleep，等到timeout时间到后即使链表没数据也返回。所以，epoll_wait非常高效。
```

```
那么，这个准备就绪list链表是怎么维护的呢？当我们执行epoll_ctl时，
除了把socket放到epoll文件系统里file对象对应的红黑树上之外，还会给内核中断处理程序注册一个回调函数，
告诉内核，如果这个句柄的中断到了，就把它放到准备就绪list链表里。
所以，当一个socket上有数据到了，内核在把网卡上的数据copy到内核中后就来把socket插入到准备就绪链表里了。
（当网卡里面有数据的时候，会发起硬件中断，提醒内核有数据到来可以拷贝数据。当网卡通知内核有数据的时候，会产生一个回调函数，
这个回调函数是epoll_ctl创建的时候，向内核里面注册的。回调函数会把当前有数据的socket（文件句柄）取出，放到list列表中。
这样就可以把存放着数据的socket发送给用户态，减少遍历的时间，和数据的拷贝）
```