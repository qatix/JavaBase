package com.qatix.base.elasticsearch.doc;

import com.qatix.base.elasticsearch.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-update.html
 *
 * @Author: Logan.Tang
 * @Date: 2018/11/1 6:17 PM
 */
public class UpdateExample {

    private static void way1(RestHighLevelClient client) throws IOException {
        System.out.println("\nway1:");
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update");

        UpdateRequest request = new UpdateRequest("posts", "doc", "1").doc(jsonMap);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println("status:" + response.status());
        System.out.println(response.toString());
    }

    private static void way2(RestHighLevelClient client) throws IOException {
        System.out.println("\nway2:");
        String jsonString = "{" +
                "\"updated\":\"2017-01-01\"," +
                "\"reason\":\"daily update\"" +
                "}";

        UpdateRequest request = new UpdateRequest("posts", "doc", "2").doc(jsonString, XContentType.JSON);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println("status:" + response.status());
        System.out.println(response.toString());
    }

    private static void way3(RestHighLevelClient client) throws IOException {
        System.out.println("\nway3:");
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "zhang");
            builder.field("updateDate", new Date());
            builder.field("message", "this is way3 by XContentBuilder-updated");
        }
        builder.endObject();

        UpdateRequest request = new UpdateRequest("posts", "doc", "3").doc(builder);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println("status:" + response.status());
        System.out.println(response.toString());
    }

    private static void upsert(RestHighLevelClient client) throws IOException {
        System.out.println("\nupsert:");
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "wu");
            builder.field("updateDate", new Date());
            builder.field("message", "this is upsert by XContentBuilder-updated");
        }
        builder.endObject();

        UpdateRequest request = new UpdateRequest("posts", "doc", "3").doc(builder);

        String jsonString = "{\"created\":\"2017-01-01\"}";
        request.upsert(jsonString, XContentType.JSON);

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println("status:" + response.status());
        System.out.println(response.toString());
    }

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(Config.ES_HOST, Config.ES_PORT, Config.ES_SCHEMA)
                ));

        way1(client);
        way2(client);
        way3(client);
        upsert(client);

        client.close();
    }
}
