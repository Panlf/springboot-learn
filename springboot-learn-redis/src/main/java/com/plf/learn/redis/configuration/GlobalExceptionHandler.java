package com.plf.learn.redis.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常类
 * @author Panlf
 * @date 2019/12/26
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value =Exception.class)
    public String exceptionHandler(Exception e){
        log.error("捕获异常为{}",e.getMessage());
        return e.getMessage();
    }
}
