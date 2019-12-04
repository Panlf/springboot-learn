package com.plf.learn.share.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author Panlf
 * @date 2019/4/19
 */
@SpringBootApplication
@EnableRedisHttpSession
public class SpringBootShareSessionApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootShareSessionApplication.class, args);
    }
}
