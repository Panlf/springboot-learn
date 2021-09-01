package com.plf.learn.limiter.annotations;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author panlf
 * @date 2021/9/1
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiRateLimiter {
    String value() default "";

    /**
     * 每秒令牌数
     * @return
     */
    long token() default Long.MAX_VALUE;

    /**
     * 获取令牌的等待时间
     * @return
     */
    int timeout() default 1;

    /**
     * 超时时间单位
     * @return
     */
    TimeUnit timeunit() default TimeUnit.SECONDS;
}

