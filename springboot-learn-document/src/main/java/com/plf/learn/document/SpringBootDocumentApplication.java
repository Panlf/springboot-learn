package com.plf.learn.document;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Panlf
 * @date 2020/2/13
 */
@SpringBootApplication
@EnableAsync
public class SpringBootDocumentApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDocumentApplication.class,args);
    }
}
