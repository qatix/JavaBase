package com.qatix.base.hive.simlepool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveJdbcUtil {
    public static final Logger log = LoggerFactory.getLogger(HiveJdbcUtil.class);

    private static HiveJdbcPool pool = new HiveJdbcPool();

    public static Connection getConnection(){
        try {
            return pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Hive JDBC get connection error:{}"+e.getMessage());
        }
        return null;
    }

    public static ResultSet executeQuery(Connection conn,String sql){
        Statement st;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void release(Connection conn,Statement st,ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void releaseConn(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
