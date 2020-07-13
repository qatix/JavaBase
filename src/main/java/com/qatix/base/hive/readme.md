





错误：Error: Could not open client transport with JDBC Uri: jdbc:hive2://s1:10000/hive: Failed to open new session: java.lang.RuntimeException: org.apache.hadoop.ipc.RemoteException(org.apache.hadoop.security.authorize.AuthorizationException): User: xxx  is not allowed to impersonate anonymous (state=08S01,code=0)



解决方式：在hadoop的配置文件core-site.xml增加如下配置，重启hdfs，其中“xxx”是连接beeline的用户，将“xxx”替换成自己的用户名即可
```
    <property>
	    <name>hadoop.proxyuser.xxx.hosts</name>
	    <value>*</value>
	</property>
	<property>
	    <name>hadoop.proxyuser.xxx.groups</name>
	    <value>*</value>
	</property>
```