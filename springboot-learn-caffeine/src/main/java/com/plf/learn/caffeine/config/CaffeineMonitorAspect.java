package com.plf.learn.caffeine.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author panlf
 * @date 2021/8/16
 */
@Aspect
@Component
// add 缓存的时候。我需要这个aop在执行缓存操作之后再执行。所以需要执行order大一点
@Order(9)
@Slf4j
public class CaffeineMonitorAspect {
    @Pointcut("execution(* com.plf.learn.caffeine.service.*.*(..))")
    public void aspect() { }

    @Resource
    CaffeineCacheManager caffeineCacheManager;


    //主要是为了监控是否数据真的放入到Cache里
    @AfterReturning("aspect()")
    public void after(JoinPoint point) {
        String method = point.getSignature().getName();
        Object[] args = point.getArgs();
       log.info("method : {}",method);
       log.info("args length: {}",args.length);

       Integer userId = 0;
       if(method.startsWith("add")){
           userId = Integer.valueOf(args[0].toString());
       }


        Collection<String>  collection =  caffeineCacheManager.getCacheNames();
        Integer finalUserId = userId;

        log.info("finalUserId : {}",finalUserId);
        collection.stream().forEach(x->{
            if(x.equalsIgnoreCase("userInfo")){
                Cache cache = caffeineCacheManager.getCache(x);
                Cache.ValueWrapper valueWrapper = cache.get(finalUserId);
                if(valueWrapper!=null){
                    log.info("cache : {} ",valueWrapper.get());
                }
            }
        });
    }

}
