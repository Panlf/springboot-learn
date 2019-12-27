package com.plf.learn.elasticsearch.controller;

import com.plf.learn.elasticsearch.bean.Book;
import com.plf.learn.elasticsearch.service.BookService;
import com.plf.learn.elasticsearch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Panlf
 * @date 2019/12/27
 */
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private IndexService indexService;


    @GetMapping("/save")
    public String save(Book book){
        bookService.saveBook(book);
        return "success";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        bookService.deleteBook(id);
        return "success";
    }

    @GetMapping("/get/{name}")
    public List<Book> findByName(@PathVariable String name){
        return bookService.findBookByName(name);
    }

    @GetMapping("/create")
    public String createIndex(){
        indexService.createIndex(Book.class);
        return "success";
    }
}
