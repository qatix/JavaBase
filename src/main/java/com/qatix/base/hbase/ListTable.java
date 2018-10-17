package com.qatix.base.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

public class ListTable {


    public static void main(String args[]) throws IOException {
        System.out.println("hello hbase");

        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", Config.ZK_QUORUM);
        configuration.set("hbase.zookeeper.property.clientPort", Config.ZK_CLIENTPORT);
        Connection connection = null;
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        // Getting all the list of tables using HBaseAdmin object
        HTableDescriptor[] tableDescriptor = admin.listTables();

        // printing all the table names.
        System.out.println("tables:");
        for (HTableDescriptor aTableDescriptor : tableDescriptor) {
            System.out.println(aTableDescriptor.getNameAsString());
        }
    }
}
