package com.qatix.groovy.module

import groovy.sql.Sql

//http://groovy-lang.org/databases.html
@Grab('mysql:mysql-connector-java:5.1.49')
//@Grab(group='mysql', module='mysql-connector-java', version='8.0.23')
@GrabConfig(systemClassLoader = true)
def url = 'jdbc:mysql://localhost:3306/test'
def user = 'root'
def password = '123456'
def driver = 'com.mysql.jdbc.Driver'
def sql = Sql.newInstance(url, user, password, driver)

//println(sql)
sql.eachRow("`select * from person`") {
    print it[0]
    print it.name
    print it.age
}


rowNum = 0
sql.eachRow('SELECT id, name,age,createDate FROM person') { row ->
    def id = row[0]
    def name = row.name
    println(name)
//    assert expected[rowNum++] == "$first $last"
}


sql.close()
