package com.plf.learn.solr.entity;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;


/**
 * @author Panlf
 * @date 2020/3/18
 */
@Data
@SolrDocument(solrCoreName = "worker")
public class Worker {
    @Field("workid")
    private String workid;

    @Field("position")
    private String position;

    @Field("salary")
    private double salary;
}
