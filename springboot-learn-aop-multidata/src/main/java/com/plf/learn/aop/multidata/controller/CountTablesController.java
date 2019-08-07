package com.plf.learn.aop.multidata.controller;

import com.plf.learn.aop.multidata.service.CountTablesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panlf
 * @date 2019/8/7
 */
@RestController
@Slf4j
public class CountTablesController {

    @Autowired
    private CountTablesService countTablesService;

    @GetMapping("count")
    public String getCount(){
       Integer userCount =  countTablesService.countUser();
       Integer commentCount =  countTablesService.countComment();
       log.info("house_user中的用户表总量为{}",userCount);
       log.info("house_comment中的评价表总量为{}",commentCount);
       return "success";
    }
}
