package com.qatix.base.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class FileExample {
    private static void fileCat() throws IOException {
        String uri = "hdfs://s211:9000/input/1.txt";
        Configuration con = new Configuration();//加载配置文件，并放在堆空间中
        FileSystem fs = FileSystem.get(URI.create(uri), con);//用FileSystem的get方法得到HDFS文件系统的实例对象。
        InputStream in;
        in = fs.open(new Path(uri));
        IOUtils.copyBytes(in, System.out, 1024, true);
        fs.close();
    }

    private static void fileRead() throws IOException {
        String uri = "hdfs://s211:9000/input/out.txt";
        Configuration con = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), con);
        FSDataInputStream in = fs.open(new Path(uri));
        byte buffer[] = new byte[256];
        int byteRead = 0;
        while ((byteRead = in.read(buffer)) > 0) {
            System.out.write(buffer, 0, byteRead);
        }
        fs.close();
    }

    private static void fileWrite() throws IOException {
        String uri = "hdfs://s211:9000/input/out.txt";
        Configuration con = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), con);
        FSDataOutputStream out = fs.create(new Path(uri));
        out.write("this is from java write text".getBytes());
        fs.close();
    }

    private static void listFiles() throws IOException {
        String uri = "hdfs://s211:9000/";
        Configuration con = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), con);
        Path paths[] = new Path[1];//准备一个数组用于存放目录下所有文件的源信息
        paths[0] = new Path("/");
        FileStatus[] status = fs.listStatus(paths);//listStatus方法用于列出目录下的所有文件（文件的地址源信息）
        Path[] listedPaths = FileUtil.stat2Paths(status);//用FileUtil工具类将FileSatus类型的数组转换为Path类型的数组
        for (Path path : listedPaths) {
            System.out.println(path);//遍历数组，打印目录下的所有文件
        }
        fs.close();
    }

    private static void mkdir() throws IOException {
        Configuration con = new Configuration();
        FileSystem fs = FileSystem.get(URI.create("hdfs://s211:9000/"), con);
        fs.mkdirs(new Path("/java/test/l3"));//mkdirs方法可以创建多层目录
        fs.close();
    }

    private static void deleteDir() throws IOException {
        Configuration con = new Configuration();
        FileSystem fs = FileSystem.get(URI.create("hdfs://s211:9000/"), con);
        fs.delete(new Path("/java/test/l3"), true);//mkdirs方法可以创建多层目录
        fs.close();
    }


    public static void main(String[] args) throws IOException {
//        fileCat();
        fileWrite();
        System.out.println("====");
        fileRead();
//        mkdir();
//        listFiles();
//        deleteDir();
//        System.out.println("after delete");
//        listFiles();

    }
}
