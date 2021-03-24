package com.qatix.solr.query;

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

public class SolrQueryExample {
    private final static String SOLR_URL = "http://localhost:8983/solr/solrdemo";

    public static void main(String[] args) throws IOException, SolrServerException {
        SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL)
                .allowCompression(true)
                .build();
        SolrQuery query = new SolrQuery();
        query.setQuery("example");
        query.set("df","name"); // set default search field
        query.setStart(0);
        query.setRows(10);

        QueryResponse response = solrClient.query(query);
        SolrDocumentList solrDocs = response.getResults();
        System.out.println("resultCount:" + solrDocs.size());
        for (SolrDocument doc : solrDocs) {
            String docId = (String) doc.getFieldValue("id");
            Object docName = doc.getFieldValue("name");
            System.out.println("found doc id:" + docId + " ,name:" + docName);
        }
    }

}
