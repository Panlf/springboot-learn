package com.plf.learn.shiro.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**
 * @author Panlf
 * @date 2019/6/5
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(HttpServletRequest req,Exception e){
        if(e instanceof UnauthorizedException){
            return "noperssion";
        }
        return "defaultException";
    }
}
