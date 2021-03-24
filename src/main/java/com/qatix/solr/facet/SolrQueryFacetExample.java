package com.qatix.solr.facet;

import com.qatix.solr.pojo.Foo;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.io.IOException;
import java.util.List;

public class SolrQueryFacetExample {
    private final static String SOLR_URL = "http://localhost:8983/solr/techprod";

    public static void main(String[] args) throws IOException, SolrServerException {
        SolrClient solrClient = new HttpSolrClient.Builder(SOLR_URL)
                .allowCompression(true)
                .build();
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setStart(0);
        query.setRows(1);
        query.setFacet(true);
        query.addFacetField("cat");
        query.setFacetMinCount(2);
        query.setShowDebugInfo(true);

        QueryResponse response = solrClient.query(query);
        System.out.println("facet count:"+response.getFacetFields().size());
        System.out.println("facets:"+response.getFacetFields());

        for (FacetField ff:response.getFacetFields()){
            System.out.println("facet: "+ff.getName() + " : " + ff.getValueCount());
            ff.getValues().forEach(c->{
                System.out.println("  >> "+c.getName()+" : " + c.getCount() + " filterQuery: " + c.getAsFilterQuery() );
            });
        }
        System.out.println(response.getFieldStatsInfo()); // null
        System.out.println(response.getFacetRanges()); // null
    }
}
