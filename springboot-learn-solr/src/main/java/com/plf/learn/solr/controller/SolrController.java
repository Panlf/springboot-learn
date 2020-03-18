package com.plf.learn.solr.controller;

import com.plf.learn.solr.entity.Worker;
import com.plf.learn.solr.service.SolrService;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Panlf
 * @date 2020/3/18
 */
@RestController
public class SolrController {

    @Autowired
    private SolrService solrService;

    @RequestMapping("save")
    public String saveWorker(Worker worker){
        solrService.saveWorker(worker);
        return "success";
    }

    @RequestMapping("get")
    public SolrDocument getWorker(String id){
       return solrService.findById(id);
    }

    @RequestMapping("getAll")
    public List<Worker> getAllWorker(){
        return solrService.findAll();
    }
}
