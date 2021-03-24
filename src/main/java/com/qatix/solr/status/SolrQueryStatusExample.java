package com.qatix.solr.status;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SolrPingResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

public class SolrQueryStatusExample {
    private final static String SOLR_URL = "http://localhost:8983/solr/";

    public static void main(String[] args) throws IOException, SolrServerException {
        SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL)
                .allowCompression(true)
                .build();
        SolrPingResponse pingResponse = solrClient.ping("solrdemo");
        int status = pingResponse.getStatus();
        System.out.println("status="+status);
        System.out.println(pingResponse.getRequestUrl());
        System.out.println(pingResponse.getResponseHeader());
        System.out.println(pingResponse.getResponse());
        System.out.println(pingResponse.getElapsedTime());


    }

}
