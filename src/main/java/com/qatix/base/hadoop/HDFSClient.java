package com.qatix.base.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;

import static org.apache.hadoop.fs.FileSystem.get;

public class HDFSClient {

    public static final String HDFS_URI = "hdfs://s211:9000";
    private FileSystem fs;

    public HDFSClient() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS",HDFS_URI);
        conf.set("dfs.replication","2");
        fs = get(conf);
    }

    private void testAddFile(){
        Path src = new Path("/Users/tang/Downloads/vue-ui.rar");
        Path dst = new Path("/tmp2/vue.rar");
        try {
            fs.copyFromLocalFile(src,dst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void testDownloadFile() throws IOException {
        Path src = new Path("/tmp2/vue.rar");
        Path dst = new Path("/Users/tang/Downloads/test_from_hdfs.rar");
        fs.copyToLocalFile(src,dst);
    }

    /**
     * 从这个结果可以看出来，namenode是不会存数据的
     * @throws IOException
     */
    private void testListFiles() throws IOException {
        Path src = new Path("/");
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(src,true);
        while (listFiles.hasNext()){
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println(fileStatus.getPath().getName());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getLen());

            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation b1 : blockLocations){
                System.out.println("block-length:" + b1.getLength() + "--" + "block-offset:" + b1.getOffset());
                String[] hosts = b1.getHosts();
                System.out.println("hosts:");
                for (String host:hosts){
                    System.out.println(host);
                }
            }
            System.out.println("-----------------------");
        }

    }

    public void close(){
        try {
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {
        HDFSClient client = new HDFSClient();
//        System.out.println("add file");
//        client.testAddFile();
//        System.out.println("add done");

//        System.out.println("download file");
//        client.testDownloadFile();
//        System.out.println("download done");

        System.out.println("list dir file");
        client.testListFiles();
        System.out.println("list dir done");


        client.close();
    }
}
