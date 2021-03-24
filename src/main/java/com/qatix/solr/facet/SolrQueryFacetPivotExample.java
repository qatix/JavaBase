package com.qatix.solr.facet;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;

public class SolrQueryFacetPivotExample {
    private final static String SOLR_URL = "http://localhost:8983/solr/films";

    public static void main(String[] args) throws IOException, SolrServerException {
        SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL)
                .allowCompression(true)
                .build();
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setStart(0);
        query.setRows(1);
        query.setFacet(true);
        query.setFacetMinCount(5);
        query.addFacetPivotField("genre_str,directed_by_str");
        query.setShowDebugInfo(true);

        QueryResponse response = solrClient.query(query);
        System.out.println("facet count:"+response.getFacetPivot().size());

        response.getFacetPivot().forEach(p->{
            System.out.println("name:"+p.getKey());
            p.getValue().forEach(v->{
                System.out.println(" >> " + v.getField() + " " + v.getValue()+ " " + v.getCount());
                v.getPivot().forEach(vp->{
                    System.out.println("    >> " + vp.getField() +  " " + vp.getValue() + " " + vp.getCount());
                });
            });
        });
    }
}
