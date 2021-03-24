package com.qatix.solr.indexing;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SolrJIndexingNestDoc {

    private final static String SOLR_URL = "http://localhost:8983/solr/solrdemo";

    public static void main(String[] args) {
        SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL).build();
        SolrInputDocument document5 = getDocument(5, "Document example 5");
        try {
            solrClient.add(Arrays.asList(document5));
            solrClient.commit();
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("indexed done");
    }

    private static SolrInputDocument getDocument(int id, String name) {

        final SolrInputDocument p1 = new SolrInputDocument();
        p1.setField("id", id);
        p1.setField("type_s", "PRODUCT");
        p1.setField("name", name);
        p1.setField("description_t", "The Cadillac of office staplers ...");
        {
            final SolrInputDocument s1 = new SolrInputDocument();
            s1.setField("id", "P11!S21");
            s1.setField("type_s", "SKU");
            s1.setField("color_s", "RED");
            s1.setField("price_i", 42);
            {
                final SolrInputDocument m1 = new SolrInputDocument();
                m1.setField("id", "P11!D41");
                m1.setField("type_s", "MANUAL");
                m1.setField("name_s", "Red Swingline Brochure");
                m1.setField("pages_i", 1);
                m1.setField("content_t", "...");

                s1.addChildDocument(m1);
            }

            final SolrInputDocument s2 = new SolrInputDocument();
            s2.setField("id", "P11!S31");
            s2.setField("type_s", "SKU");
            s2.setField("color_s", "BLACK");
            s2.setField("price_i", 3);

            final SolrInputDocument m1 = new SolrInputDocument();
            m1.setField("id", "P11!D51");
            m1.setField("type_s", "MANUAL");
            m1.setField("name_s", "Quick Reference Guide");
            m1.setField("pages_i", 1);
            m1.setField("content_t", "How to use your stapler ...");

            final SolrInputDocument m2 = new SolrInputDocument();
            m2.setField("id", "P11!D61");
            m2.setField("type_s", "MANUAL");
            m2.setField("name_s", "Warranty Details");
            m2.setField("pages_i", 42);
            m2.setField("content_t", "... lifetime guarantee ...");

            p1.addChildDocuments(Arrays.asList(s1, s2, m1, m2));
        }

        return p1;
    }

}