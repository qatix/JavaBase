package com.qatix.base.elasticsearch.doc;

import com.qatix.base.elasticsearch.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 5:27 PM
 */
public class DeleteExample {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(Config.ES_HOST, Config.ES_PORT, Config.ES_SCHEMA)
                ));

        DeleteRequest request = new DeleteRequest("posts","doc","1");
        request.timeout("2m");

        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
//        request.version(1);
//        request.versionType(VersionType.EXTERNAL);

//        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);

        client.deleteAsync(request, RequestOptions.DEFAULT, new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                String index = deleteResponse.getIndex();
                String type = deleteResponse.getType();
                String id = deleteResponse.getId();
                long version = deleteResponse.getVersion();

                System.out.println("index:" + index);
                System.out.println("type:" + type);
                System.out.println("id:" + id);
                System.out.println("version:" + version);

                ReplicationResponse.ShardInfo shardInfo = deleteResponse.getShardInfo();
                if(shardInfo.getTotal() != shardInfo.getSuccessful()){
                    //Handle the situation where number of successful shards is less than total shards
                    System.err.println("total is not equal success");
                }

                if(shardInfo.getFailed() > 0){
                    for (ReplicationResponse.ShardInfo.Failure failure:shardInfo.getFailures()){
                        String reason = failure.reason();
                        System.out.println("fail reason:" + reason);
                    }
                }

                if(deleteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND){
                    //the document to be deleted was not found
                    System.err.println("doc not found");
                }
            }

            @Override
            public void onFailure(Exception e) {
                System.err.println("onFailure");
                e.printStackTrace();
            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.close();
    }
}
