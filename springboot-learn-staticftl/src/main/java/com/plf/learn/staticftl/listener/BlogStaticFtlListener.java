package com.plf.learn.staticftl.listener;

import com.plf.learn.staticftl.bean.Blog;
import com.plf.learn.staticftl.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Panlf
 * @date 2019/11/18
 */
@Component
public class BlogStaticFtlListener {

    @Autowired
    private BlogService blogService;

    @JmsListener(destination="staticftl.blog")
    public void ListenBlogQueue(String message){

        String blog_id = message;

        //获取到信息
        Blog blog = blogService.getById(blog_id);
        Map<String,Object> map = new HashMap<>();
        map.put("blog",blog);
        blogService.productStaticPage(map,blog_id);
    }
}
