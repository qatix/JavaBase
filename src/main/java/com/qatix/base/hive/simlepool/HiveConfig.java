package com.qatix.base.hive.simlepool;

import org.apache.logging.log4j.util.PropertiesUtil;

import java.util.Properties;

public class HiveConfig {
    public static PropertiesUtil properties =
            new PropertiesUtil("hive.properties");

    static String jdbcPoolInitSize = properties.getStringProperty("db.jdbcPoolInitSize");

    /**
     * Hive JDBC Driver类
     */
    static String dbDriver = properties.getStringProperty("db.driver");

    /**
     *  hiveserver2 的ip地址
     */
    static String dbIP = properties.getStringProperty("db.ip");

    /**
     * hiveserver2 的端口
     */
    static String dbPort = properties.getStringProperty("db.port");

    /**
     * hiveserver2连接数据库名称
     */
    public static String dbName = properties.getStringProperty("db.name");
    /**
     *
     * hiveserver2连接用名名称
     */
    final static String dbUsername = properties.getStringProperty("db.username");

    /**
     * hiveserver2连接用名密码
     */
    final static String dbPassword = properties.getStringProperty("db.password");

    /**
     * hiveserver2连接是否开启Kerberos认证
     */
    final static String iskrb5 = properties.getStringProperty("db.iskrb5");

    /**
     * hiveserver2开启Kerberos认证主体
     */
    final static String krb5Principal = properties.getStringProperty("hive.principal");

    /**
     * Hive集群上的HDFS存储目录
     */
    static String storageDirectory = properties.getStringProperty("storage.directory");
}
