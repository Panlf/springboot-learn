package com.plf.learn.staticftl.controller;

import com.plf.learn.staticftl.bean.Blog;
import com.plf.learn.staticftl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panlf
 * @date 2019/11/15
 */
@RequestMapping("/blog")
@RestController
public class BlogController  {

    @Autowired
    private BlogService blogService;


    @GetMapping("save")
    public String saveBlog(Blog blog){
        blog.setId(System.currentTimeMillis()+"");
        blogService.save(blog);
        return "success";
    }
}
