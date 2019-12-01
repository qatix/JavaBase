package com.qatix.base.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRead {

    public static Map<String, Object> resultToMap(Result result) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        List<Cell> listCell = result.listCells();
        Map<String, Object> tempMap = new HashMap<String, Object>();
        String rowname = "";
        List<String> familynamelist = new ArrayList<String>();
        for (Cell cell : listCell) {
            byte[] rowArray = cell.getRowArray();
            byte[] familyArray = cell.getFamilyArray();
            byte[] qualifierArray = cell.getQualifierArray();
            byte[] valueArray = cell.getValueArray();
            int rowoffset = cell.getRowOffset();
            int familyoffset = cell.getFamilyOffset();
            int qualifieroffset = cell.getQualifierOffset();
            int valueoffset = cell.getValueOffset();
            int rowlength = cell.getRowLength();
            int familylength = cell.getFamilyLength();
            int qualifierlength = cell.getQualifierLength();
            int valuelength = cell.getValueLength();

            byte[] temprowarray = new byte[rowlength];
            System.arraycopy(rowArray, rowoffset, temprowarray, 0, rowlength);
            String temprow = Bytes.toString(temprowarray);
//            System.out.println(Bytes.toString(temprowarray));

            byte[] tempqulifierarray = new byte[qualifierlength];
            System.arraycopy(qualifierArray, qualifieroffset, tempqulifierarray, 0, qualifierlength);
            String tempqulifier = Bytes.toString(tempqulifierarray);
//            System.out.println(Bytes.toString(tempqulifierarray));

            byte[] tempfamilyarray = new byte[familylength];
            System.arraycopy(familyArray, familyoffset, tempfamilyarray, 0, familylength);
            String tempfamily = Bytes.toString(tempfamilyarray);
//            System.out.println(Bytes.toString(tempfamilyarray));

            byte[] tempvaluearray = new byte[valuelength];
            System.arraycopy(valueArray, valueoffset, tempvaluearray, 0, valuelength);
//            String tempvalue= Bytes.toString(tempvaluearray);
            String tempvalue = new String(tempvaluearray);
//            System.out.println(Bytes.toString(tempvaluearray));


            tempMap.put(tempfamily + ":" + tempqulifier, tempvalue);
//            long t= cell.getTimestamp();
//            tempMap.put("timestamp",t);
            rowname = temprow;
            String familyname = tempfamily;
            if (familynamelist.indexOf(familyname) < 0) {
                familynamelist.add(familyname);
            }
        }
        resMap.put("rowname", rowname);
        for (String familyname : familynamelist) {
            HashMap<String, Object> tempFilterMap = new HashMap<String, Object>();
            for (String key : tempMap.keySet()) {
                String[] keyArray = key.split(":");
                if (keyArray[0].equals(familyname)) {
                    tempFilterMap.put(keyArray[1], tempMap.get(key));
                }
            }
            resMap.put(familyname, tempFilterMap);
        }

        return resMap;
    }

    public static void main(String args[]) throws IOException {
        System.out.println("hello hbase");

        String tableName = "order";
        String columnFamily = "s";

        HBaseCilent eg = new HBaseCilent();
//        eg.createTable(tableName,columnFamily);
//        System.out.println("create table done");

        Result res = eg.getRow(tableName, "XSD15102600100018");

//        System.out.println(resultToMap(res).toString());
//        eg.scan(tableName);

        byte[] bs = res.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes("name"));
        String s = new String(bs);
        System.out.println(s);

        bs = res.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes("qty"));
        String qty = new String(bs);
        System.out.println(qty);


        bs = res.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes("price"));
        String price = new String(bs);
        System.out.println(price);


    }
}
