package com.qatix.groovy.module

//http://groovy-lang.org/databases.html

import groovy.sql.Sql
url='jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8'
driver='com.mysql.cj.jdbc.Driver'
username='root'
passwd='123456'
//指定maven仓库
//@GrabResolver(name = 'aliyun', root = 'http://maven.aliyun.com/nexus/content/groups/public/')
//加载数据库连接驱动包
@Grab('mysql:mysql-connector-java:5.1.49')
@GrabConfig(systemClassLoader = true)
//创建sql实例
def sql = Sql.newInstance(url, username, passwd, driver)

sql.eachRow("select * from person") {
    println it[0]  //可以按表字段从左到右依次下标访问
    println it.name  //直接按字段名访问
}
//支持元数据闭包显示
sql.eachRow("select * from person",{ meta->
    //元数据闭包只会调用一次
    meta.columnCount.times {
        print meta.getColumnName(it+1)+"  "
        print meta.getColumnClassName(it+1)+"  "
        print meta.getColumnDisplaySize(it+1)+"  "
        print meta.getColumnType(it+1)+"---"
        println meta.getColumnTypeName(it+1)
    }
}) {
    //可以按表字段从左到右依次下标访问或者字段名字访问
    println it[0]+"  "+it.name
}

//sql.close()