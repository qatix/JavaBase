package com.qatix.base.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.apache.hadoop.fs.FileSystem.get;

public class StreamAccess {

    public static final String HDFS_URI = "hdfs://s211:9000";
    private FileSystem fs;

    public StreamAccess() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", HDFS_URI);
        conf.set("dfs.replication", "2");
        fs = get(conf);
    }

    public static void main(String[] args) throws IOException {

        StreamAccess sa = new StreamAccess();

        sa.testUpload();
        sa.testDownload();
        sa.testRandomAccess();
        sa.testCat();
//        sa.testCat2();
        sa.testDelete();

        sa.close();
    }

    private void testUpload() throws IOException {
        System.out.println("func:" + Thread.currentThread().getStackTrace()[1].getMethodName());
        FSDataOutputStream os = fs.create(new Path("/test.spf"), true);
        FileInputStream is = new FileInputStream("/Users/tang/Documents/localhost.spf");
        IOUtils.copyBytes(is, os, 4096);
    }

    private void testDownload() throws IOException {
        System.out.println("func:" + Thread.currentThread().getStackTrace()[1].getMethodName());
        FSDataInputStream is = fs.open(new Path("/test.spf"));
        FileOutputStream os = new FileOutputStream(new File("/Users/tang/Downloads/test_localhost.spf"));
        IOUtils.copyBytes(is, os, 4096);
    }

    private void testRandomAccess() throws IOException {
        System.out.println("func:" + Thread.currentThread().getStackTrace()[1].getMethodName());
        FSDataInputStream is = fs.open(new Path("/test.spf"));
//        is.seek(10);
        FileOutputStream os = new FileOutputStream(new File("/Users/tang/Downloads/test_localhost.spf"));
        IOUtils.copyBytes(is, os, 30000L, true);
    }

    //
    private void testCat() throws IOException {
        System.out.println("func:" + Thread.currentThread().getStackTrace()[1].getMethodName());
        FSDataInputStream is = fs.open(new Path("/tmp/LICENSE.txt"));
        IOUtils.copyBytes(is, System.out, 1024);
        is.close();
//        PrintStream ps = new PrintStream(new File("/Users/tang/Downloads/12.txt"));
//        IOUtils.copyBytes(is,ps,1024);
//        is.close();
//        ps.close();
    }

    private void testCat2() throws IOException {
        System.out.println("func:" + Thread.currentThread().getStackTrace()[1].getMethodName());
        FSDataInputStream is = fs.open(new Path("/test.spf"));

        FileStatus[] listStatus = fs.listStatus(new Path("/test.spf"));
        BlockLocation[] fileBlockLocations = fs.getFileBlockLocations(listStatus[0], 0, listStatus[0].getLen());
        System.out.println(fileBlockLocations.length);
        System.out.println(fileBlockLocations[0]);

        long length = fileBlockLocations[0].getLength();
        long offset = fileBlockLocations[0].getOffset();

        System.out.println(length);
        System.out.println(offset);

        IOUtils.copyBytes(is, System.out, (int) length);

        byte[] bs = new byte[4096];

        FileOutputStream os = new FileOutputStream(new File("/Users/tang/Downloads/block"));
        while (is.read(bs, 0, 4096) != -1) {
            os.write(bs);
            offset += 4096;

            if (offset >= length) {
                return;
            }
        }

        os.flush();
        os.close();
        is.close();
    }

    private void testDelete() throws IOException {
        System.out.println("func:" + Thread.currentThread().getStackTrace()[1].getMethodName());
        //删除文件夹，如果是非空文件夹，参数2必须给值true
        fs.delete(new Path("/test.spf"), false);
    }

    public void close() {
        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
