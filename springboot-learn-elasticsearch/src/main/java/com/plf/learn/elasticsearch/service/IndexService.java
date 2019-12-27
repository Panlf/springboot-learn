package com.plf.learn.elasticsearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Panlf
 * @date 2019/12/27
 */
@Service
public class IndexService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    public void createIndex(Class<?> indexClass){
        // 创建索引，会根据对应类的@Document注解信息来创建
        elasticsearchTemplate.createIndex(indexClass);
        // 配置映射，会根据对应类中的id、Field等字段来自动完成映射
        elasticsearchTemplate.putMapping(indexClass);
    }

    public void deleteIndex(Class<?> indexClass){
        elasticsearchTemplate.deleteIndex(indexClass);
    }
}
