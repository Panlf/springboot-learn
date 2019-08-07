package com.plf.learn.aop.multidata.config;

import java.lang.reflect.Method;

import com.plf.learn.aop.multidata.annotation.TargetDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author Panlf
 * @date 2019/8/7
 */
@Order(1)   //设置AOP执行顺序(需要在事务之前，否则事务只发生在默认库中)
@Aspect
@Component
@Slf4j
public class DataSourceAspect {

    @Pointcut("execution(* com.plf.learn.aop.multidata.service.*.*(..))")
    public void aspect() { }

    @Before("aspect()")
    private void before(JoinPoint point) {
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        // 获取目标类
        Class<?> classz = target.getClass();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz.getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource data = m.getAnnotation(TargetDataSource.class);
                // 数据源放到当前线程中
                log.info("当前数据源 -- {} 添加到当前线程",data.value());
                DynamicDataSourceHolder.putDataSource(data.value());
            }
        } catch (Exception e) {
            //默认选择 数据源放到当前线程中
            DynamicDataSourceHolder.putDataSource("house_user");
        }

    }

    @AfterReturning("aspect()")
    public void after(JoinPoint point) {
        DynamicDataSourceHolder.removeDataSource();
    }
}
