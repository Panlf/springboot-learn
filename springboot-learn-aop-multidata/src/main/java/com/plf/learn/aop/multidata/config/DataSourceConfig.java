package com.plf.learn.aop.multidata.config;

/**
 * @author Panlf
 * @date 2019/8/7
 */
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url_user}")
    private String urlUser;
    @Value("${spring.datasource.url_comment}")
    private String urlComment;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Primary
    @Bean(name = "house_user")
    public DataSource getUserDataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setDriverClassName(driverClass);
        config.setPassword(password);
        config.setJdbcUrl(urlUser);
        return new HikariDataSource(config);
    }

    @Bean(name = "house_comment")
    public DataSource getCommentDataSource() {
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setDriverClassName(driverClass);
        config.setPassword(password);
        config.setJdbcUrl(urlComment);
        return new HikariDataSource(config);
    }


    @Bean(name = "dataSource")
    public DataSource dataSource() {
        //按照目标数据源名称和目标数据源对象的映射存放在Map中
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("house_user",getUserDataSource());
        targetDataSources.put("house_comment",getCommentDataSource());
        //采用是想AbstractRoutingDataSource的对象包装多数据源
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        //设置默认的数据源，当拿不到数据源时，使用此配置
        dataSource.setDefaultTargetDataSource(getUserDataSource());
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 事物
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

