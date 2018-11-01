package com.qatix.base.elasticsearch.search;

import com.qatix.base.elasticsearch.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 7:12 PM
 */
public class SearchExample {
    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(Config.ES_HOST, Config.ES_PORT, Config.ES_SCHEMA)
                ));

        SearchRequest request = new SearchRequest("posts");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("user", "tang"));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        searchSourceBuilder.profile(true);
        request.source(searchSourceBuilder);

        request.types("doc");

        request.routing("routing");

        //Setting IndicesOptions controls how unavailable indices are resolved and how wildcard expressions are expanded
        request.indicesOptions(IndicesOptions.lenientExpandOpen());

        //Use the preference parameter e.g. to execute the search to prefer local shards. The default is to randomize across shards.
        request.preference("_local");

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        RestStatus status = response.status();
        System.out.println("status:" + status);

        TimeValue took = response.getTook();
        System.out.println("time cost:" + took);

        Boolean terminatedEarly = response.isTerminatedEarly();
        System.out.println("terminatedEarly:" + terminatedEarly);

        boolean timedOut = response.isTimedOut();
        System.out.println("timeout:" + timedOut);

        int totalShards = response.getTotalShards();
        int successfulShards = response.getSuccessfulShards();
        int failedShards = response.getFailedShards();
        System.out.println("totalShards : " + totalShards + " successfulShards : " + successfulShards + " failedShards : " + failedShards);

        for (ShardSearchFailure failure : response.getShardFailures()) {
            // failures should be handled here
            System.err.println("failure - reason:" + failure.reason());
        }

        SearchHits hits = response.getHits();
        System.out.println("totalHits:" + hits.getTotalHits());
        System.out.println("maxScore:" + hits.getMaxScore());

        SearchHit[] searchHits = hits.getHits();
        int hitIdx = 1;
        for (SearchHit hit : searchHits) {
            System.out.println("result hit:" + hitIdx++);
            System.out.println(hit.getIndex() + "|"+ hit.getType() + "|" + hit.getId() + "|" + hit.getScore());
            System.out.println(hit.getSourceAsString());
            System.out.println(hit.toString());
            System.out.println("hightlightFields:" + hit.getHighlightFields().toString());
            System.out.println("-------------------");
        }

        client.close();
    }
}
