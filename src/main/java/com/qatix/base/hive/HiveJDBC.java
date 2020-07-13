package com.qatix.base.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HiveJDBC {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://192.168.199.211:10000/default";
    private static String user = "root";
    private static String password = "123456";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static void main(String[] args) throws Exception {
        HiveJDBC hj = new HiveJDBC();
        hj.init();
        hj.useDatabase();
        drop(stmt);
        hj.dropDatabase();
        hj.createDatabase();
        hj.useDatabase();
        hj.createTable();
        hj.showDatabases();
        hj.loadData();
        hj.selectData();
        drop(stmt);
        hj.dropDatabase();
        hj.destory();
    }

    public void init() throws Exception {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
        stmt = conn.createStatement();
    }

    public void createDatabase() throws Exception {
        String sql = "create database hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    public void useDatabase() throws Exception {
        String sql = "use hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    public void dropDatabase() throws Exception {
        String sql = "drop database if exists hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    public void showDatabases() throws Exception {
        String sql = "show databases";
        System.out.println("Running: " + sql + "\n");
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }

    public void createTable() throws Exception {
        String sql = "create table t2(id int ,name String) row format delimited fields terminated by ','";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    public void loadData() throws Exception {
        String filePath = "/Users/tang/github/JavaBase/test/pokes.txt";
        String sql = "load data local inpath '" + filePath + "' overwrite into table t2";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    public void selectData() throws Exception {
        String sql = "select * from t2";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        System.out.println("编号" + "\t" + "姓名");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
        }
    }

    public static void drop(Statement stmt) throws Exception {
        String dropSQL = "drop table t2";
        boolean bool = stmt.execute(dropSQL);
        System.out.println("删除表是否成功:" + bool);
    }

    public void destory() throws Exception {
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
}

