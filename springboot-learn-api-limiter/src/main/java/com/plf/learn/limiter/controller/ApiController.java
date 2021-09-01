package com.plf.learn.limiter.controller;

import com.plf.learn.limiter.annotations.ApiRateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author panlf
 * @date 2021/9/1
 */
@RestController
public class ApiController {

    private AtomicInteger num = new AtomicInteger(0);

    @ApiRateLimiter(token = 1,timeout = 1)
    @GetMapping("hello")
    public String hello(){
        return "处理成功 --- "+num.incrementAndGet();
    }
}
