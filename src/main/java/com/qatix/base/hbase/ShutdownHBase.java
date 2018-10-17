package com.qatix.base.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

public class ShutdownHBase {


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
        //类似于./bin/stop-hbase.sh
        admin.shutdown();
        System.out.println("hbase shutdown");
    }
}
