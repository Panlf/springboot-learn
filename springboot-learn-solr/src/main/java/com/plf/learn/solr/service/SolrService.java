package com.plf.learn.solr.service;

import com.plf.learn.solr.entity.Worker;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Panlf
 * @date 2020/3/18
 */
@Service
public class SolrService {
    @Autowired
    private SolrClient solrClient;

    public void saveWorker(Worker worker){
        SolrInputDocument document = new SolrInputDocument();
        document.setField("workid",worker.getWorkid());
        document.setField("salary",worker.getSalary());
        document.setField("position",worker.getPosition());

        try {
            solrClient.add(document);
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SolrDocument findById(String id){
        SolrDocument solrDocument=null;
        try {
            solrDocument = solrClient.getById(id);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return solrDocument;
    }

    public List<Worker> findAll(){
        List<Worker> workerList = new ArrayList<Worker>();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        try {
            QueryResponse queryResponse = solrClient.query(solrQuery);
            if (queryResponse != null){
                workerList = queryResponse.getBeans(Worker.class);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workerList;
    }
}
