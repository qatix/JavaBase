package com.qatix.base.elasticsearch.doc;

import com.qatix.base.elasticsearch.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 6:42 PM
 */
public class MultiGetExample {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(Config.ES_HOST, Config.ES_PORT, Config.ES_SCHEMA)
                ));


        MultiGetRequest request = new MultiGetRequest();
        request.add(new MultiGetRequest.Item(
                "posts",
                "doc",
                "1"
        ));
        request.add(new MultiGetRequest.Item(
                "posts",
                "doc",
                "2"
        ));
        request.add(new MultiGetRequest.Item(
                "posts",
                "doc",
                "3"
        ).fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE));

        String[] includes = new String[]{"user"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        request.add(new MultiGetRequest.Item("posts", "doc", "4").fetchSourceContext(fetchSourceContext));

        request.refresh(true);
        request.realtime(false);

        MultiGetResponse responses = client.mget(request, RequestOptions.DEFAULT);
        for (MultiGetItemResponse item : responses.getResponses()) {
            System.out.println(item.getResponse().toString());
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.close();
    }
}
