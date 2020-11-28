package com.qatix.base.hive.druidpool;

import javax.activation.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveDruidUtilMain {
    public static void main(String[] args) {
        Connection conn = HiveDruidUtil.getHiveConn();
        Statement stmt = null;
        if(conn == null){
            System.err.println("get connection fail:null");
        }else{
            System.out.println("get connection successfully");
            try {
                stmt = conn.createStatement();
                ResultSet res = stmt.executeQuery("select 1");
                int i = 0;
                while(res.next()){
                    if(i<10){
                        System.out.println(res.getString(1));
                        i++;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
