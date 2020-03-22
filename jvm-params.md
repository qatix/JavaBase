
## java启动参数共分为三类；
    其一是标准参数（-），所有的JVM实现都必须实现这些参数的功能，而且向后兼容；
    其二是非标准参数（-X），默认jvm实现这些参数的功能，但是并不保证所有jvm实现都满足，且不保证向后兼容；
    其三是非Stable参数（-XX），此类参数各个jvm实现会有所不同，将来可能会随时取消，需要慎重使用；

## 标准参数中比较有用的：
verbose
-verbose:class
输出jvm载入类的相关信息，当jvm报告说找不到类或者类冲突时可此进行诊断。
-verbose:gc
输出每次GC的相关情况。
-verbose:jni
输出native方法调用的相关情况，一般用于诊断jni调用错误信息。

非标准参数又称为扩展参数

一般用到最多的是
-Xms512m 设置JVM促使内存为512m。此值可以设置与-Xmx相同，以避免每次垃圾回收完成后JVM重新分配内存。
-Xmx512m ，设置JVM最大可用内存为512M。
-Xmn200m：设置年轻代大小为200M。整个堆大小=年轻代大小 + 年老代大小 + 持久代大小。持久代一般固定大小为64m，所以增大年轻代后，将会减小年老代大小。此值对系统性能影响较大，Sun官方推荐配置为整个堆的3/8。
-Xss128k：

设置每个线程的堆栈大小。JDK5.0以后每个线程堆栈大小为1M，以前每个线程堆栈大小为256K。更具应用的线程所需内存大小进行调整。在相同物理内 存下，减小这个值能生成更多的线程。但是操作系统对一个进程内的线程数还是有限制的，不能无限生成，经验值在3000~5000左右。
-Xloggc:file
与-verbose:gc功能类似，只是将每次GC事件的相关情况记录到一个文件中，文件的位置最好在本地，以避免网络的潜在问题。
若与verbose命令同时出现在命令行中，则以-Xloggc为准。
-Xprof

跟踪正运行的程序，并将跟踪数据在标准输出输出；适合于开发环境调试。

用-XX作为前缀的参数列表在jvm中可能是不健壮的，SUN也不推荐使用，后续可能会在没有通知的情况下就直接取消了；但是由于这些参数中的确有很多是对我们很有用的，比如我们经常会见到的-XX:PermSize、-XX:MaxPermSize等等；

http://blog.csdn.net/sdujava2011/article/details/50086933




1、堆的大小可以通过 -Xms 和 -Xmx 来设置，一般将他们设置为相同的大小，目的是避免在每次垃圾回收后重新调整堆的大小，比如 -Xms=2g -Xmx=2g 或者 -Xms=512m -Xmx=512m

2、年轻代大小可以通过 -Xmn 来设置，比如-Xmn=2g 或者 -Xmn512m，此值对系统性能影响较大，Sun官方推荐配置为整个堆的3/8

3、年老代大小 = 堆大小 – 年轻代大小

4、持久代或者永久代大小可以通过 -XX:PermSize 和 -XX:MaxPermSize 来控制

5、-XX:SurvivorRatio 控制 Eden和Survivor的内存占用比例，默认为8；

如果设置了NewRatio，那么整个堆空间的1/(NewRatio +1)就是新生代空间的大小，-XX:NewRatio推荐2到4.

如果同时指定了NewRatio和NewSize，你应该使用更大的那个。于是，当堆空间被创建时，你可以用过下面的表达式计算初始新生代空间的大小：

 	
min(MaxNewSize, max(NewSize, heap/(NewRatio+1)))
三、JVM内存溢出配置

如何能在JVM遇到OOM错误的时候能够打印heap dump?可以设置-XX:+HeapDumpOnOutOfMemoryError参数，让JVM在探测到内存OOM的时候打印dump。但是在JVM启动参数添加这个参数的时候，JVM启动失败：Unrecognized VM option '+HeapDumpOnOutOfMemeryError' ,问题的原因是因为没有添加-XX:HeapDumpPath参数配置。-XX:HeapDumpPath这个参数可以设置dump文件的存放位置。将JVM启动参数设置成如下格式：

-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:/

问题得到解决。当JVM发生内存溢出的时候，会在C:/下打印出heap dump

 

常用参数设置

UseParNewGC表示对新生代采用并行gc；

ParallelGCThreads表示并行的线程数为8，一般是cpu的核个数，当核个数大于8时可能不是很适用；

UseConcMarkSweepGC表示对full gc采用CMS gc；

-XX:+DisableExplicitGC 表示禁止显式gc，System.gc()

-XX:+UseCMSCompactAtFullCollection 适用于CMS gc，表示在进行gc的同时清理内存碎片，但会加长gc的总时间

-XX:CMSInitiatingOccupancyFraction=80 适用于CMS gc，表示在年老代达到80%使用率时马上进行回收

在JVM Crash时获heap信息的一些配置参数：

-XX:ErrorFile=./xxx.log JVM Crash时记录heap信息

-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./yyy.log JVM OOM时记录heap信息

http://www.cnblogs.com/moonandstar08/p/4924602.html

 

Trace跟踪参数

-verbose:gc

-XX:+printGC

-XX:+PrintGCDetails

-XX:+PrintGCTimeStamps

-Xloggc:log/gc.log // 指定GC log的位置，以文件输出

-XX:PrintHeapAtGC // 每一次GC后，都打印堆信息

// 类加载信息

-XX:TraceClassLoading

 

-XX:+PrintClassHistogram

-Ctrl +Break 打印类信息， 类的使用情况