package com.qatix.base.hbase;

import java.sql.*;

public class DbExample {

    private static Connection conn;

    public static void main(String args[]) {
        DbExample eg = new DbExample();
        eg.init();
        eg.doTask();
        eg.doTask2();
        eg.close();
    }

    public void init() {
        // 不同的数据库有不同的驱动
        String url = "jdbc:mysql://localhost:3306/erp";
        String user = "root";
        String password = "123456";

        try {
            // 加载驱动
//            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接成功..");
        } catch (SQLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    public void doTask() {
        try {
            String tableName = "order";
            String columnFamily = "s";

            HBaseCilent eg = new HBaseCilent();
            eg.createTable(tableName, columnFamily);

            int count = 0;

            Statement stmt = conn.createStatement();//创建数据库操作
            String sql = "select s.date_added,sd.sales_id,s.no,sd.name,sd.business_type,sd.quantity,sd.sale_price,sd.deduction,sd.stock_cost,s.bill_date from sales_detail sd left join sales s ON(s.sales_id=sd.sales_id) where sd.ref_type=1 and s.status=1 and sd.name != '' limit 1000"; //开发中不允许写“*”
            ResultSet rs = stmt.executeQuery(sql); //数据查询操作
            while (rs.next()) { //移动指针同时判断是否还有数据行
                String name = rs.getString("name");
                String no = rs.getString("no");
                String quantity = rs.getString("quantity");
                String salePrice = rs.getString("sale_price");
                System.out.println(name + " | " + no + " | " + quantity + " | " + salePrice);

                if (null == no) {
                    continue;
                }
                eg.putRow(tableName, no, columnFamily, "name", name);
                eg.putRow(tableName, no, columnFamily, "qty", quantity);
                eg.putRow(tableName, no, columnFamily, "price", salePrice);
                eg.putRow(tableName, no, columnFamily, "bt", rs.getString("business_type"));
                eg.putRow(tableName, no, columnFamily, "deduct", rs.getString("deduction"));
                eg.putRow(tableName, no, columnFamily, "cost", rs.getString("stock_cost"));
                eg.putRow(tableName, no, columnFamily, "date", rs.getString("bill_date"));

                count += 3;
            }
            System.out.println("put records:" + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doTask2() {
        try {
            String tableName = "member";
            String columnFamily = "s";

            HBaseCilent eg = new HBaseCilent();
            eg.createTable(tableName, columnFamily);

            int count = 0;

            Statement stmt = conn.createStatement();//创建数据库操作
            String sql = "select  vip_user_id as id,name,mobile,balance,credits,card_number as card_no from vip_user where name!='' and is_deleted=0"; //开发中不允许写“*”
            ResultSet rs = stmt.executeQuery(sql); //数据查询操作
            while (rs.next()) { //移动指针同时判断是否还有数据行
                String id = rs.getString("id");
                eg.putRow(tableName, "id_" + id, columnFamily, "name", rs.getString("name"));
                eg.putRow(tableName, "id_" + id, columnFamily, "mobile", rs.getString("mobile"));
                eg.putRow(tableName, "id_" + id, columnFamily, "balance", rs.getString("balance"));
                eg.putRow(tableName, "id_" + id, columnFamily, "credits", rs.getString("credits"));
                if (rs.getString("card_no").length() > 0) {
                    eg.putRow(tableName, "id_" + id, columnFamily, "card_no", rs.getString("card_no"));
                }

                count += 3;
            }
            System.out.println("put records:" + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
