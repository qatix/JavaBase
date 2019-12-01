package com.qatix.base.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HBaseCilent {

    private Connection connection;

    public HBaseCilent() {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", Config.ZK_QUORUM);
        configuration.set("hbase.zookeeper.property.clientPort", Config.ZK_CLIENTPORT);
        System.out.println("constructed");
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(configuration.get("hbase.master"));
    }

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
            String tempvalue = Bytes.toString(tempvaluearray);
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

        String tableName = "test3";
        String columnFamily = "cf";
        String columnFamily2 = "cd";
        String[] columnFamilies = {columnFamily, columnFamily2};

        HBaseCilent eg = new HBaseCilent();
        eg.createTable(tableName, columnFamilies);
        eg.addColumnFamily(tableName, "cd");
        eg.addColumnFamily(tableName, "rt");
//        eg.createTable(tableName,columnFamily2);

        eg.putRow(tableName, "row1", columnFamily, "a", "v1");
        eg.putRow(tableName, "row1", columnFamily, "b", "v2");
        eg.putRow(tableName, "row1", columnFamily, "c", "v3");
        eg.putRow(tableName, "row2", columnFamily, "a", "v4");
        eg.putRow(tableName, "row2", columnFamily2, "a", "v4000");
        eg.putRow(tableName, "row2", columnFamily2, "b", "v4111");
        eg.putRow(tableName, "row3", columnFamily, "b", "v5");
        eg.putRow(tableName, "row4", columnFamily, "c", "v6");

        eg.getRow(tableName, "row1");

        eg.scan(tableName);

        System.out.println("---delete qualifier------");
        eg.deleteQualifier(tableName, "row1", columnFamily, "b");
        eg.scan(tableName);

        System.out.println("---delete columnfamily------");
        eg.deleteColumnFamily(tableName, columnFamily2);
        eg.scan(tableName);

        System.out.println("---delete row------");
        eg.deleteRow(tableName, "row3");
        eg.scan(tableName);

        eg.testInsertData(tableName, columnFamily);


//        eg.deleteTable(tableName);
    }

    public void createTable(String tableName, String[] columnFamilies) throws IOException {
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        if (admin.tableExists(tableName)) {
            System.out.println(tableName + " exists!");
        } else {
            HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
            for (String columnFamily : columnFamilies) {
                tableDesc.addFamily(new HColumnDescriptor(columnFamily));
            }
            admin.createTable(tableDesc);
            System.out.println(tableName + " create successfully");
        }
    }

    public void createTable(String tableName, String columnFamily) throws IOException {
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        if (admin.tableExists(tableName)) {
            System.out.println(tableName + " exists!");
        } else {
            HTableDescriptor tableDesc = new HTableDescriptor(TableName.valueOf(tableName));
            tableDesc.addFamily(new HColumnDescriptor(columnFamily));
            admin.createTable(tableDesc);
            System.out.println(tableName + " create successfully");
        }
    }

    public boolean addColumnFamily(String tableName, String columnFamily) throws IOException {
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        if (admin.tableExists(tableName)) {
            try {
                admin.addColumn(tableName, new HColumnDescriptor(columnFamily));
                System.out.println(tableName + " add columnFamily:" + columnFamily + " successfully");
                return true;
            } catch (InvalidFamilyOperationException e) {
                System.err.println("Exception: " + columnFamily + " is alread existed");
                return false;
            }
        } else {
            System.out.println(tableName + " not exists!");
            return false;
        }
    }

    public void putRow(String tableName, String row, String columnFamily,
                       String column, String data) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(row));
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
        table.put(put);
        System.out.println("putString '" + row + "','" + columnFamily + ":" + column
                + "','" + data + "'");
    }

    public void putRow(String tableName, String row, String columnFamily,
                       String column, long data) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(row));
        put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
        table.put(put);
        System.out.println("putLong '" + row + "','" + columnFamily + ":" + column
                + "','" + data + "'");
    }

    public Result getRow(String tableName, String row) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(row));
        Result result = table.get(get);
        System.out.println("Get: " + result);
        return result;
    }

    public void scan(String tableName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        ResultScanner rs = table.getScanner(scan);
        for (Result r : rs) {
            System.out.println("Scan: " + r);
        }
    }

    public boolean deleteTable(String tableName) throws IOException {
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        if (admin.tableExists(tableName)) {
            try {
                admin.disableTable(tableName);
                admin.deleteTable(tableName);
                System.out.println("table " + tableName + " deleted");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /**
     * 删除以后，该columnFamily必须重建才行
     *
     * @param tableName
     * @param columnFamily
     * @return
     * @throws IOException
     */
    public boolean deleteColumnFamily(String tableName, String columnFamily) throws IOException {
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        if (admin.tableExists(tableName)) {
            try {
                admin.deleteColumn(tableName, columnFamily);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean deleteQualifier(String tableName, String rowName, String columnFamily, String qualifierName) throws IOException {
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        Table table = connection.getTable(TableName.valueOf(tableName));
        if (admin.tableExists(tableName)) {
            try {

                Delete delete = new Delete(rowName.getBytes());
                delete.addColumns(columnFamily.getBytes(), qualifierName.getBytes());
                table.delete(delete);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean deleteRow(String tableName, String rowName) throws IOException {
        HBaseAdmin admin = (HBaseAdmin) connection.getAdmin();
        Table table = connection.getTable(TableName.valueOf(tableName));
        if (admin.tableExists(tableName)) {
            try {
                Delete delete = new Delete(rowName.getBytes());
                table.delete(delete);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    //过滤列
    public String queryColumn(String tableName, String prefix) throws IOException {
        Filter filter = new ColumnPrefixFilter(Bytes.toBytes(prefix));
        FilterList filterList = new FilterList();
        filterList.addFilter(filter);
        return query(tableName, filterList);
    }

    public String queryRowCount(String tableName, String startRowName, String count) throws IOException {
        Filter filter = new PageFilter(Integer.parseInt(count));
        FilterList filterList = new FilterList();
        filterList.addFilter(filter);

        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan s = new Scan(Bytes.toBytes(startRowName));
        s.setFilter(filterList);
        ResultScanner rs = table.getScanner(s);

        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (Result r : rs) {
            Map<String, Object> temp = resultToMap(r);
            resList.add(temp);
        }
        return resList.toString();
    }

    public String queryEqual(String tableName, String columnFamily, String qualifier, String data) throws IOException {
        Filter filter = new SingleColumnValueFilter(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier),
                CompareFilter.CompareOp.EQUAL, Bytes.toBytes(data));
        FilterList filterList = new FilterList();
        filterList.addFilter(filter);
        return query(tableName, filterList);
    }

    public String queryBetween(String tableName, String columnFamily, String qualifier, String minData, String maxData) throws IOException {
        Filter minFilter = new SingleColumnValueFilter(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier),
                CompareFilter.CompareOp.GREATER_OR_EQUAL, Bytes.toBytes(minData));

        Filter maxFilter = new SingleColumnValueFilter(Bytes.toBytes(columnFamily), Bytes.toBytes(qualifier),
                CompareFilter.CompareOp.LESS_OR_EQUAL, Bytes.toBytes(maxData));

        FilterList filterList = new FilterList();
        filterList.addFilter(minFilter);
        filterList.addFilter(maxFilter);

        return query(tableName, filterList);
    }

    public String query(String tableName, FilterList filterList) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Scan s = new Scan();
        s.setFilter(filterList);
        ResultScanner rs = table.getScanner(s);

        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
        for (Result r : rs) {
            Map<String, Object> temp = resultToMap(r);
            resList.add(temp);
        }
        return resList.toString();
    }

    public void testInsertData(String tableName, String columnFamily) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        for (int i = 10; i < 2000; i++) {
            Put put = new Put(Bytes.toBytes("row" + i));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes("a"), Bytes.toBytes(i));
            table.put(put);
            System.out.println("put '" + "row" + i + "' : " + i);
        }
    }
}
