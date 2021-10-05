package com.plf.learn.limiter.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.plf.learn.limiter.annotations.ApiRateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author panlf
 * @date 2021/9/1
 */
@Slf4j
@Aspect
@Component
public class ApiRateLimiterAspect {

    //@Resource
    //private HttpServletResponse response;

    private static final ConcurrentHashMap<String,RateLimiter> RATE_LIMITER_CACHE = new ConcurrentHashMap<>();

    /**
     * 根据注解切入
     */
    @Pointcut("@annotation(com.plf.learn.limiter.annotations.ApiRateLimiter)")
    public void pointcut() { }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("拦截到了{}方法...", joinPoint.getSignature().getName());
        Object obj = null;
        // 获取目标方法
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method targetMethod = methodSignature.getMethod();

        if (targetMethod.isAnnotationPresent(ApiRateLimiter.class)) {
            // 获取目标方法的@ApiRateLimiter注解
            ApiRateLimiter rateLimit = targetMethod.getAnnotation(ApiRateLimiter.class);

            if(RATE_LIMITER_CACHE.get(signature.getName())==null){
                RATE_LIMITER_CACHE.put(signature.getName(), RateLimiter.create(rateLimit.token()));
            }

            if(RATE_LIMITER_CACHE.get(signature.getName())!=null &&
                    !RATE_LIMITER_CACHE.get(signature.getName()).tryAcquire(rateLimit.timeout(), rateLimit.timeunit())) {
                log.info("=====    接口并发量过大    =====");
                System.out.println("error=>"+System.currentTimeMillis());
               throw new RuntimeException("请求频繁，请稍后再试");
            }
        }
        System.out.println("success=>"+System.currentTimeMillis());
        return joinPoint.proceed();
    }
}
