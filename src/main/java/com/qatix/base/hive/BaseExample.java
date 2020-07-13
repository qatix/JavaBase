package com.qatix.base.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseExample {

    // 驱动，固定的
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    // 默认就是10000端口，ip地址使用hive服务器的
    private static String url = "jdbc:hive2://localhost:10000/default";
    // hive连接的用户名和密码，默认就算是下面这两个
    private static String user = "root";
    private static String password = "123456";

    // 公共使用的变量
    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    // 加载驱动、创建连接
    public static void init() throws Exception {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
    }

    // 释放资源
    public static void destory() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    // 测试代码（每次都需要现在加载，执行万后释放）
    public static void main(String[] args) throws Exception {
        init();
        dropTable();
        // 创建表功能通过
        createTable();
        // 显示表名称
        showTables();
        // 显示表描述
        descTable();
        // 本地数据导入
        loadData();
        // 查询数据
        selectData();
        // 运行mapreduce作业
        countData();
        // 执行删除
//        dropTable();
        destory();
    }

    // 创建表
    public static void createTable() throws Exception {
        String sql = "create table pokes (foo int, bar string) row format delimited fields terminated by ','";
        stmt.execute(sql);
    }

    // 查询所有表
    public static void showTables() throws Exception {
        String sql = "show tables";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    // 查看表结构
    public static void descTable() throws Exception {
        String sql = "desc pokes";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1) + "\t" + rs.getString(2));
        }
    }

    // 加载数据
    public static void loadData() throws Exception {
        String filePath = "/Users/tang/github/JavaBase/test/pokes.txt";
        String sql = "load data local inpath '" + filePath + "' overwrite into table pokes";
        stmt.execute(sql);
    }

    // 查询数据
    public static void selectData() throws Exception {
        String sql = "select * from pokes";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString("foo") + "\t\t" + rs.getString("bar"));
        }
    }

    // 统计查询（会运行mapreduce作业）
    public static void countData() throws Exception {
        String sql = "select count(1) from pokes";
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt(1));
        }
    }

    // 删除数据库表
    public static void dropTable() throws Exception {
        String sql = "drop table if exists pokes";
        stmt.execute(sql);
    }
}
