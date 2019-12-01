package com.qatix.base.elasticsearch.doc;

import com.qatix.base.elasticsearch.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 5:02 PM
 * @see https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-index.html
 */
public class IndexExample {

    private static void testAdd(RestHighLevelClient client) throws IOException {
        System.out.println("\ntestAdd:");
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "tang");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "xxxxx");

        IndexRequest request = new IndexRequest("posts", "doc", "10").source(jsonMap);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("status:" + response.status());
        System.out.println(response.toString());
    }

    private static void way1(RestHighLevelClient client) throws IOException {
        System.out.println("\nway1:");
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "tang");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out ElasticSearch");

        IndexRequest request = new IndexRequest("posts", "doc", "1").source(jsonMap);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("status:" + response.status());
        System.out.println(response.toString());
    }

    private static void way2(RestHighLevelClient client) throws IOException {
        System.out.println("\nway2:");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"this is way2\"" +
                "}";

        IndexRequest request = new IndexRequest("posts", "doc", "2").source(jsonString, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("status:" + response.status());
        System.out.println(response.toString());
    }

    private static void way3(RestHighLevelClient client) throws IOException {
        System.out.println("\nway3:");
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "zhang");
            builder.field("postDate", new Date());
            builder.field("message", "this is way3 by XContentBuilder");
        }
        builder.endObject();

        IndexRequest request = new IndexRequest("posts", "doc", "3").source(builder);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("status:" + response.status());
        System.out.println(response.toString());
    }

    private static void way4(RestHighLevelClient client) throws IOException {
        System.out.println("\nway4:");
        IndexRequest request = new IndexRequest("posts", "doc", "4")
                .source("user", "wang", "postDate", new Date(), "message", "this is way4 by sourcemap");

        //timeout to wait for primary shard to become available as as TimeValue
        request.timeout(TimeValue.timeValueSeconds(1));
//        equal to
// request.timeout("1s");

        request.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        request.setRefreshPolicy("wait_for");

        //set version
//        request.version(2);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println("status:" + response.status());
        System.out.println(response.toString());
    }


    private static void way5(RestHighLevelClient client) throws IOException {
        System.out.println("\nway5:");
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "zhang");
            builder.field("postDate", new Date());
            builder.field("message", "this is way5 by Async");
        }
        builder.endObject();

        IndexRequest request = new IndexRequest("posts", "doc", "5").source(builder);

        client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                System.out.println("async status:" + indexResponse.status());
                System.out.println(indexResponse.toString());
            }

            @Override
            public void onFailure(Exception e) {
                System.err.println("onFailure:");
                e.printStackTrace();
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(Config.ES_HOST, Config.ES_PORT, Config.ES_SCHEMA)
                ));

        way1(client);
        way2(client);
        way3(client);
        way4(client);
        way5(client);
        testAdd(client);

        //成功后，可通过http://t90:9201/posts/doc/_search 或者 http://t90:9201/posts/doc/1 查看

        client.close();

        System.out.println("done");
    }
}
