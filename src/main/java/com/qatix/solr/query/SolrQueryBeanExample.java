package com.qatix.solr.query;

import com.qatix.solr.pojo.Foo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SolrQueryBeanExample {
    private final static String SOLR_URL = "http://localhost:8983/solr/techprod";

    public static void main(String[] args) throws IOException, SolrServerException {
        SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL)
                .allowCompression(true)
                .build();
        SolrQuery query = new SolrQuery();
        query.setQuery("apple");
        query.setStart(0);
        query.setRows(10);
        query.setHighlight(true);
        query.setShowDebugInfo(true);
        query.addHighlightField("name,author,manu");
        query.setHighlightSimplePre("<b>");
        query.setHighlightSimplePost("</b>");

        QueryResponse response = solrClient.query(query);
        List<Foo> fooList = response.getBeans(Foo.class);
        System.out.println("resultCount:" + fooList.size());
        for (Foo foo : fooList) {
            System.out.println(foo.toString());
        }
        System.out.println(response.getHighlighting());
        System.out.println(response.getDebugMap());
    }
}
