package com.qatix.solr.indexing;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SolrJIndexing {

    private final static String SOLR_URL = "http://localhost:8983/solr/solrdemo";

    public static void main(String[] args) {
        SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).build();
        SolrInputDocument document1 = getDocument(1, "Document example 1");
        SolrInputDocument document2 = getDocument(2, "Document example 2");
        SolrInputDocument document3 = getDocument(3, "Document example 3 exmaple example-kd");
        SolrInputDocument document4 = getDocument(4, "Document example 2 exampe test-example");
        Collection<SolrInputDocument> docs = new ArrayList<>();
        docs.add(document1);
        docs.add(document2);
        docs.add(document3);
        docs.add(document4);
        try {
            solrClient.add(docs);
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("indexed docs:" + docs.size());
    }

    private static SolrInputDocument getDocument(int id, String name) {
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", id);
        document.addField("name", name);
        return document;
    }

}