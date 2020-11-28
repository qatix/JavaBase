package com.qatix.base.hive.simlepool;


import javax.sql.DataSource;
import java.io.PrintWriter;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Vector;
import java.util.logging.Logger;

public class HiveJdbcPool implements DataSource {

    private static final Vector<Connection> connectionList =
            new Vector<>();

    static {
        try {
            int jdbcPoolInitSize = Integer.parseInt(HiveConfig.jdbcPoolInitSize);
            StringBuilder urlSB = new StringBuilder();
            Class.forName(HiveConfig.dbDriver);
            urlSB.append("jdbc:hive2://").append(HiveConfig.dbIP).append(":").append(HiveConfig.dbPort);
            if ("true".equalsIgnoreCase(HiveConfig.iskrb5)) {
                urlSB.append("/;principal=").append(HiveConfig.krb5Principal);
            }
            String dbURL = urlSB.toString();
            for (int i = 0; i < jdbcPoolInitSize; i++) {
                Connection conn = DriverManager.getConnection(dbURL, HiveConfig.dbUsername, HiveConfig.dbPassword);
                connectionList.add(conn);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw new SQLException("Create Hive Connection failed");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        synchronized (connectionList) {
            if (!connectionList.isEmpty()) {
                final Connection conn = connectionList.remove(0);
                return (Connection) Proxy.newProxyInstance(
                        HiveJdbcPool.class.getClassLoader(),
                        new Class[]{Connection.class},
                        (proxy, method, args) -> {
                            if ("close".equals(method.getName())) {
                                //return to pool
                                connectionList.add(conn);
                                return null;
                            } else {
                                return method.invoke(conn, args);
                            }
                        }
                );
            } else {
                throw new RuntimeException("No connection available");
            }
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
