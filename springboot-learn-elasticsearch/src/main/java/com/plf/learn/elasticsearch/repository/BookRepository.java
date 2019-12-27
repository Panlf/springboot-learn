package com.plf.learn.elasticsearch.repository;

import com.plf.learn.elasticsearch.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ElasticsearchCrudRepository 就是SpringBoot自带的CRUD
 * @author Panlf
 * @date 2019/12/27
 */
public interface BookRepository extends ElasticsearchRepository<Book,Long> {
}
