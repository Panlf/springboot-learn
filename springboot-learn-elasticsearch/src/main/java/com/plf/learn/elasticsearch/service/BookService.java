package com.plf.learn.elasticsearch.service;

import com.plf.learn.elasticsearch.bean.Book;
import com.plf.learn.elasticsearch.repository.BookRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Panlf
 * @date 2019/12/27
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void saveBook(Book book){
        bookRepository.save(book);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }

    public Book findById(Long id){
        Optional<Book> book=bookRepository.findById(id);
        return book.get();
    }

    public List<Book> findBookByName(String name){
        /*
         matchQuery：词条匹配，先分词然后在调用termQuery进行匹配
         TermQuery：词条匹配，不分词
         wildcardQuery：通配符匹配
         fuzzyQuery：模糊匹配
         rangeQuery：范围匹配
         booleanQuery：布尔查询
         */
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("name",name));
        // 搜索，获取结果
        Page<Book> bookPage = bookRepository.search(queryBuilder.build());

        return bookPage.toList();
    }
}
