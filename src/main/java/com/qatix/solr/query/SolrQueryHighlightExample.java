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

public class SolrQueryHighlightExample {
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
//        query.addHighlightField("name");
//        query.setHighlightFragsize(2);
//        query.setHighlightSnippets(2);
        query.addHighlightField("name,author,manu");
        query.setHighlightSimplePre("<b>");
        query.setHighlightSimplePost("</b>");

        QueryResponse response = solrClient.query(query);
        SolrDocumentList solrDocs = response.getResults();
        System.out.println("resultCount:" + solrDocs.size());
        for (SolrDocument doc : solrDocs) {
            String docId = (String) doc.getFieldValue("id");
            Object docName = doc.getFieldValue("name");
            System.out.println("found doc id:" + docId + " ,name:" + docName);

            String highlightedText = getHighlightedText(response, "name", docId);
            System.out.println("highLightedText:" + highlightedText);
        }
    }

    public static String getHighlightedText(final QueryResponse response, String fieldName, String docId) {
        String highlightedText = "";
        Map<String, Map<String, List<String>>> highlights = response.getHighlighting();
        System.out.println("highlights:" + highlights);
        if (highlights != null && MapUtils.isNotEmpty(highlights.get(docId))) {
            List<String> snippets = highlights.get(docId).get(fieldName);
            if (CollectionUtils.isEmpty(snippets)) {
                highlightedText = getFragments(snippets);
            }
        }
        return highlightedText;
    }

    private static final String getFragments(List<String> snippets) {
        StringBuilder fragments = new StringBuilder();
        for (int i = 0; i < snippets.size(); i++) {
            if (i > 0) {
                fragments.append("........");
            }
            fragments.append(snippets.get(i));
        }
        return fragments.toString();
    }

}
