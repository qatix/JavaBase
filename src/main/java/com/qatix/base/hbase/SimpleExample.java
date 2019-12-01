package com.qatix.base.hbase;

import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class SimpleExample {

    public static void main(String args[]) throws IOException {
        System.out.println("hello hbase");

        String tableName = "test3";
        String columnFamily = "cf";

        HBaseCilent eg = new HBaseCilent();
        eg.createTable(tableName, columnFamily);
//        System.out.println("create table done");

        eg.putRow(tableName, "row1", columnFamily, "a", "v1");
        eg.putRow(tableName, "row1", columnFamily, "b", "v2");
        eg.putRow(tableName, "row1", columnFamily, "c", "v3");
        eg.putRow(tableName, "row2", columnFamily, "a", "v4");
        eg.putRow(tableName, "row3", columnFamily, "b", "v5");
        eg.putRow(tableName, "row4", columnFamily, "c", "v6");
        eg.putRow(tableName, "row4", columnFamily, "d", "中文");
        eg.putRow(tableName, "row4", columnFamily, "e", 123);
        eg.putRow(tableName, "row4", columnFamily, "f", "345");
        eg.putRow(tableName, "row4", columnFamily, "g", "aa");


        Result res = eg.getRow(tableName, "row4");

        byte[] bs = res.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes("d"));
        String s = new String(bs);
        System.out.println(s);

        bs = res.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes("e"));
        Long l = Bytes.toLong(bs);
        System.out.println(l);


        eg.scan(tableName);

//        eg.deleteTable(tableName);

    }
}
