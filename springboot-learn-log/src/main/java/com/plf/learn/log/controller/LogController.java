package com.plf.learn.log.controller;

import com.plf.learn.log.annotation.Loggable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panlf
 * @date 2020/3/18
 */
@RestController
public class LogController {

    @Loggable(value = "日志查询操作")
    @RequestMapping("/get")
    public String getLog(){
        return "success";
    }

    @Loggable(value = "带参数日志查询操作")
    @RequestMapping("/get/{name}")
    public String getLogWithValue(@PathVariable String name){
        return name;
    }
}
