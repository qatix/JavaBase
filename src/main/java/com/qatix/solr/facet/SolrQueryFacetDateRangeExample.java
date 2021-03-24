package com.qatix.solr.facet;

import com.google.gson.Gson;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.RangeFacet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * "params":{
 *       "facet.range":"initial_release_date",
 *       "facet.limit":"300",
 *       "q":"*:*",
 *       "facet.range.gap":"+1YEAR",
 *       "rows":"0",
 *       "facet":"on",
 *       "facet.range.start":"NOW-20YEAR",
 *       "facet.range.end":"NOW"}},
 */
public class SolrQueryFacetDateRangeExample {
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
        query.addDateRangeFacet(
                "initial_release_date",
                 Date.from(LocalDateTime.now().minusYears(20).toInstant(ZoneOffset.ofHours(8))),
                new Date(),
                "+1YEAR"
        );
        query.setFacetLimit(300);
        query.setFacetMinCount(2);
        query.setShowDebugInfo(true);

        System.out.println(new Gson().toJson(query));
        QueryResponse response = solrClient.query(query);
        System.out.println("range count:"+response.getFacetRanges().size());
        System.out.println("facets:"+response.getFacetRanges());

        response.getFacetRanges().forEach(rf->{
            System.out.println("range: "+rf.getName() + " : " + rf.getCounts().size());
            List<RangeFacet.Count> counts = rf.getCounts();
            counts.forEach(c->{
                System.out.println("  >> "+ c.getValue()  + " : " + c.getCount());
            });
        });

    }
}
