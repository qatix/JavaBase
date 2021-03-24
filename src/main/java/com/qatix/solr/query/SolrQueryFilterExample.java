package com.qatix.solr.query;

import com.qatix.solr.pojo.Foo;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.List;

public class SolrQueryFilterExample {
    private final static String SOLR_URL = "http://localhost:8983/solr/techprod";

    public static void main(String[] args) throws IOException, SolrServerException {
        SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL)
                .allowCompression(true)
                .build();
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setStart(0);
        query.setRows(10);

//        query.addFilterQuery("id:100-435805"); //字段过滤
        query.addFilterQuery("name:'ATI'"); //字段过滤
//        query.addFilterQuery("+name:('Euro')"); //name 包含Euro
        query.addFilterQuery("-name:('Euro')"); //name 不包含Euro

        query.addFilterQuery("price:[8.0 TO *]"); //价格大于8
//        query.addFilterQuery("price:[5.0 TO 8]"); //价格 5 < price < 8

        query.addFilterQuery("inStock:false"); //只显示无库存的
        //多个条件是and关系
//        query.setShowDebugInfo(true);

        System.out.println(query.toLocalParamsString());
        QueryResponse response = solrClient.query(query);
        List<Foo> fooList = response.getBeans(Foo.class);
        System.out.println("resultCount:" + fooList.size());
        for (Foo foo : fooList) {
            System.out.println(foo.toString());
        }
        System.out.println(response.getDebugMap());
    }

}
