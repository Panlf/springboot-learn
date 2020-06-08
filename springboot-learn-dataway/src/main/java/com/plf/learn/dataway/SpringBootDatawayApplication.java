package com.plf.learn.dataway;

import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHasor()
@EnableHasorWeb()
@SpringBootApplication
public class SpringBootDatawayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDatawayApplication.class,args);
    }
}
