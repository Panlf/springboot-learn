package com.plf.learn.jetcache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Panlf
 * @date 2019/5/24
 */
@SpringBootApplication
@EnableMethodCache(basePackages = "com.plf.learn.jetcache")
@EnableCreateCacheAnnotation
public class SpringBootJetcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJetcacheApplication.class,args);
    }
}
