package com.plf.learn.redis.controller;

import com.plf.learn.redis.annotations.ApiIdempotent;
import com.plf.learn.redis.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panlf
 * @date 2019/12/26
 */
@RestController
@Slf4j
public class RedisController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/create/{username}")
    public String createToken(@PathVariable String username){
        return tokenService.createToken(username);
    }

    @GetMapping("/order")
    @ApiIdempotent
    public String getOrder(){
        log.info("幂等性检验通过之后的业务操作");
        return "success";
    }
}
