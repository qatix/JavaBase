package com.qatix.base.elasticsearch.doc;

import com.qatix.base.elasticsearch.Config;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;

/**
 * @see  https://www.elastic.co/guide/en/elasticsearch/client/java-rest/current/java-rest-high-document-get.html
 * @Author: Logan.Tang
 * @Date: 2018/11/1 4:27 PM
 */
public class GetExample {
    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(Config.ES_HOST, Config.ES_PORT, Config.ES_SCHEMA)
                ));

        GetRequest getRequest = new GetRequest("inspections", "_doc", "2");

        //Disable source retrieval, enabled by default
//        getRequest.fetchSourceContext(FetchSourceContext.FETCH_SOURCE);

        //configurate source inclusion for specific fields
        String[] includes = new String[]{"business_address","business_city"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true,includes,excludes);
        getRequest.fetchSourceContext(fetchSourceContext);

        //set routint value
        getRequest.routing("routing");

        RequestOptions requestOptions = RequestOptions.DEFAULT;
        GetResponse response = client.get(getRequest, requestOptions);

        System.out.println("index:" + response.getIndex());
        System.out.println("exist:" + response.isExists());
        System.out.println("id:" + response.getId());
        System.out.println("type:" + response.getType());
        System.out.println("version:" + response.getVersion());

        System.out.println(response.toString());

        if (response.isExists()) {
            System.out.println("source:");
            System.out.println(response.getSource().toString());
        }

        client.close();
    }
}
/**
 * index:inspections
 * exist:true
 * id:2
 * type:_doc
 * version:1
 * {"_index":"inspections","_type":"_doc","_id":"2","_version":1,"found":true,"_source":{"business_address":"10 Mason St","business_city":"San Francisco","business_id":"60354","business_latitude":"37.783527","business_location":{"type":"Point","coordinates":[-122.409061,37.783527]},"business_longitude":"-122.409061","business_name":"Soup Unlimited","business_postal_code":"94102","business_state":"CA","inspection_date":"2016-11-23T00:00:00.000","inspection_id":"60354_20161123","inspection_type":"Routine","inspection_score":95}}
 * source:
 * {business_name=Soup Unlimited, business_address=10 Mason St, business_longitude=-122.409061, inspection_date=2016-11-23T00:00:00.000, business_city=San Francisco, business_latitude=37.783527, business_postal_code=94102, inspection_id=60354_20161123, inspection_score=95, business_id=60354, inspection_type=Routine, business_location={coordinates=[-122.409061, 37.783527], type=Point}, business_state=CA}
 */
