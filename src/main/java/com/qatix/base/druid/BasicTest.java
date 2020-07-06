package com.qatix.base.druid;

import org.apache.calcite.avatica.AvaticaConnection;
import org.apache.calcite.avatica.AvaticaStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BasicTest {
    public static void main(String[] args) throws SQLException {
        String urlStr = "jdbc:avatica:remote:url=http://localhost:8082/druid/v2/sql/avatica/";
        Properties connectionProperties = new Properties();
        AvaticaConnection connection = (AvaticaConnection) DriverManager.getConnection(urlStr, connectionProperties);
        AvaticaStatement statement = connection.createStatement();
        String sql = "SELECT * from wikipedia limit 10";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.toString());
            String comment = resultSet.getString("comment");
            System.out.println(comment);
        }
    }
}
