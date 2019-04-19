package com.plf.learn.custom.event.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局捕获异常控制器
 * @author Panlf
 * @date 2019/4/19
 */
public class GlobalExceptionController {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> resultError(){
        Map<String,Object> result = new HashMap<>();
        result.put("errCode", "500");
        result.put("errMsg", "系统错误");
        return result;
    }
}
