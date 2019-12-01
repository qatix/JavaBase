package com.qatix.base.elasticsearch.doc;

import com.qatix.base.elasticsearch.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-bulk.html
 *
 * @Author: Logan.Tang
 * @Date: 2018/11/1 6:26 PM
 */
public class BulkExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(Config.ES_HOST, Config.ES_PORT, Config.ES_SCHEMA)
                ));

        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest("posts", "doc", "3"));
        request.add(new UpdateRequest("posts", "doc", "2")
                .doc(XContentType.JSON, "other", "test"));
        request.add(new IndexRequest("posts", "doc", "6")
                .source(XContentType.JSON, "field", "baz"));

        request.timeout("2m");
        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);

//        client.bulk(request,RequestOptions.DEFAULT);
        client.bulkAsync(request, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkItemResponses) {
                System.out.println("onResponse");

                for (BulkItemResponse bulkItemResponse : bulkItemResponses) {
                    DocWriteResponse itemResponse = bulkItemResponse.getResponse();

                    //in case of failure
                    if (bulkItemResponse.isFailed()) {
                        BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                        System.err.println("failure:" + failure.getMessage());
                    }

                    if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                            || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                        IndexResponse indexResponse = (IndexResponse) itemResponse;
                        System.out.println("index-response:");
                        System.out.println(indexResponse.toString());

                    } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                        UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                        System.out.println("update-response:");
                        System.out.println(updateResponse.toString());

                    } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                        DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                        System.out.println("delete-response:");
                        System.out.println(deleteResponse.toString());
                    }
                }

            }

            @Override
            public void onFailure(Exception e) {
                System.err.println("onFailure");
                e.printStackTrace();
            }
        });

        Thread.sleep(5000);
        client.close();
    }
}
