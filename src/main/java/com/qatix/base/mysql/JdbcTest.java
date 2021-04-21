package com.qatix.base.mysql;

import java.sql.*;

public class JdbcTest {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("ClassLoad successfully");
        } catch (Exception ex) {
            System.out.println("ClassLoad fail");
            // handle the error
        }

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=Asia/shanghai",
                            "root",
                            "123456");

            System.out.println("Connect successfully");
            ps = conn.prepareStatement("select num,name from person");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.print(id + "\t" + name);
                System.out.println("");
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            System.out.println("Connect fail");
        }
    }
}
