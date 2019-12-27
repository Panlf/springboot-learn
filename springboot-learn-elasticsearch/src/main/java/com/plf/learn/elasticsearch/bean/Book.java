package com.plf.learn.elasticsearch.bean;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 单机测试，所以设置es的分片数量为1(默认5)，设置副本数量为1(默认1)
 * @author Panlf
 * @date 2019/12/27
 */
@Data
@Document(indexName = "culture",type = "book",shards = 1,replicas = 0)
public class Book {
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String name;

    @Field(type = FieldType.Keyword)
    private String description;

    @Field(type = FieldType.Double)
    private Double price;
}
