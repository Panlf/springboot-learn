package com.plf.learn.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author Panlf
 * @date 2019/7/11
 */
@SpringBootApplication
@MapperScan(basePackages={"com.plf.learn.monitor.mapper"})
public class SpringBootMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMonitorApplication.class,args);
    }
}
