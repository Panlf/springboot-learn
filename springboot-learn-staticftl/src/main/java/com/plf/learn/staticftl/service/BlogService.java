package com.plf.learn.staticftl.service;

import com.plf.learn.staticftl.bean.Blog;
import com.plf.learn.staticftl.component.ActiveMQProducer;
import com.plf.learn.staticftl.repository.BlogRepository;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Panlf
 * @date 2019/11/18
 */
@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ActiveMQProducer activeMQProducer;

    public void save(Blog blog){
        blogRepository.save(blog);
        activeMQProducer.sendMessage("staticftl.blog",blog.getId());
    }

    public List<Blog> findAll(){
        List<Blog> list = blogRepository.findAll();
        return list;
    }

    public Blog getById(String id){
        Optional<Blog> blog= blogRepository.findById(id);
        return blog.get();
    }

    /**
     * 输出静态化页面
     * @param root
     * @param id
     */
    public void productStaticPage(Map<String,Object> root, String id){
        Configuration config = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        config.setDefaultEncoding("UTF-8");
        //输出页面的全名
        String path = getStaticHtmlPath()+"/" + id + ".html";
        File f = new File(path);
        File parentFile = f.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }

        Writer out = null;
        try {
            //获取模板页面
            String templatePath1 =ClassLoader.getSystemResource("templates").getPath();
            TemplateLoader templateLoader=new FileTemplateLoader(new File(templatePath1+"\\blog"));
            config.setTemplateLoader(templateLoader);
            Template template = config.getTemplate("detail.html");

            //输出页面
            out = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");

            //处理数据
            template.process(root, out);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 魔改的Freemarker的静态化页面输出地址
     * 不具备普遍性
     * @return 静态化页面输出地址
     */
    public String getStaticHtmlPath(){
        //Thread.currentThread().getContextClassLoader().getResource("templates").getPath()
        String path = this.getClass().getClassLoader().getResource("").getPath();
        if(path==null || path.length()==0){
            return "";
        }
        path=path.substring(0,path.indexOf("build"))+"\\src\\main\\resources\\static\\html";
        return path;
    }
}
