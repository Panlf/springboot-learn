package com.plf.learn.aop.multidata.service;

import com.plf.learn.aop.multidata.annotation.TargetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 测试库
 * @author Panlf
 * @date 2019/8/7
 */
@Service
public class CountTablesService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @TargetDataSource("house_user")
    public Integer countUser(){
        return jdbcTemplate.queryForObject("select count(*) from user",Integer.class);
    }

    @TargetDataSource("house_comment")
    public Integer countComment(){
        return jdbcTemplate.queryForObject("select count(*) from comment",Integer.class);
    }
}
