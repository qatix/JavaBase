package com.qatix.base.hive.druidpool;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.logging.log4j.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class HiveDruidUtil {
    private static DruidDataSource hiveDataSource = new DruidDataSource();

    public static DruidDataSource getHiveDataSource() {
        if (hiveDataSource.isInited()) {
            return hiveDataSource;
        }

        try {
            PropertiesUtil dsProp = new PropertiesUtil("hive.properties");
            //基本属性 url、user、password
            hiveDataSource.setUrl(dsProp.getStringProperty("hive_jdbc_url"));
            hiveDataSource.setUsername(dsProp.getStringProperty("hive_jdbc_username"));
            hiveDataSource.setPassword(dsProp.getStringProperty("hive_jdbc_password"));

            //配置初始化大小、最小、最大
            hiveDataSource.setInitialSize(Integer.parseInt(dsProp.getStringProperty("hive_initialSize")));
            hiveDataSource.setMinIdle(Integer.parseInt(dsProp.getStringProperty("hive_minIdle")));
            hiveDataSource.setMaxActive(Integer.parseInt(dsProp.getStringProperty("hive_maxActive")));

            //配置获取连接等待超时的时间
            hiveDataSource.setMaxWait(Integer.parseInt(dsProp.getStringProperty("hive_maxWait")));

            //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
            hiveDataSource.setTimeBetweenEvictionRunsMillis(60000);

            //配置一个连接在池中最小生存的时间，单位是毫秒
            hiveDataSource.setMinEvictableIdleTimeMillis(300000);

            hiveDataSource.setValidationQuery("select 1");
            hiveDataSource.setTestWhileIdle(false);
            hiveDataSource.setTestOnBorrow(false);
            hiveDataSource.setTestOnReturn(false);

            //打开PSCache，并且指定每个连接上PSCache的大小
            hiveDataSource.setPoolPreparedStatements(true);
            hiveDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

            //配置监控统计拦截的filters
//            hiveDataSource.setFilters("stat");

            hiveDataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hiveDataSource;
    }

    public static void closeDataSource(){
        if(hiveDataSource != null){
            hiveDataSource.close();
        }
    }

    public static Connection getHiveConn(){
        hiveDataSource = getHiveDataSource();
        Connection conn = null;
        try {
            conn = hiveDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConn(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
