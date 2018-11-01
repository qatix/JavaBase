package com.qatix.base.elasticsearch.doc;

import com.qatix.base.elasticsearch.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/1 4:57 PM
 */
public class GetNotExistExample {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(Config.ES_HOST, Config.ES_PORT, Config.ES_SCHEMA)
                ));

        //not exist
        GetRequest getRequest = new GetRequest("doc_not_exist", "_doc", "2");
        try {
            GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                System.err.println("doc not exist");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //conflict
        getRequest = new GetRequest("inspections", "_doc", "2").version(100);
        try {
            GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {
                System.err.println("doc conflict");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        client.close();
    }
}
