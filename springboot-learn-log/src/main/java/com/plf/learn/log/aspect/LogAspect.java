package com.plf.learn.log.aspect;

import com.plf.learn.log.annotation.Loggable;
import com.plf.learn.log.entity.LogMessage;
import com.plf.learn.log.service.LogMessageService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志记录AOP形式记录
 * @author Panlf
 * @date 2020/3/18
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 操作开始时间
     */
    private Long startTime = 0L;
    /**
     * 操作结束时间
     */
    private Long endTime = 0L;

    @Autowired
    private LogMessageService logMessageService;

    /**
     * Controller层切点
     */
    @Pointcut("execution(* com.plf.learn.log.controller..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * 前置通知 在方法前使用
     *  可以验证用户或者验证Token等信息
     *  这里只是一个测试，所以我在这里注入了用户
     */
    @Before("controllerAspect()")
    public void beforeControllerLog(JoinPoint joinpoint){

    }

    @Around("controllerAspect()")
    public Object aroundControllerLog(ProceedingJoinPoint point) throws Throwable{
        //获取Request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        //获取目标方法
        Method method = ((MethodSignature) point.getSignature()).getMethod();

        //判断是否有@Loggable注解
        if(!method.isAnnotationPresent(Loggable.class)){
            //如果没有直接返回结果
            return point.proceed();
        }

        //记录日志信息
        LogMessage logMessage = new LogMessage();

        /**
         * 获取方法上的注解实例
         */
        Loggable loggable = method.getAnnotation(Loggable.class);

        //处理接口请求日志
        handleRequestLog(point, loggable, request, logMessage);

        //执行目标方法内容，获取执行结果
        Object result = point.proceed();

        //处理接口响应日志
        handleResponseLog(logMessage, loggable, result);
        return result;
    }

    private void handleRequestLog(ProceedingJoinPoint point, Loggable loggable, HttpServletRequest request, LogMessage logMessage) {
        //类名
        String targetName = point.getTarget().getClass().toString();
        //方法名称
        String methodName = point.getSignature().getName();

        Map<String, Object> params = getMethodParamNames(point);

        //判断是否输出日志
        if(loggable.console()){
            log.info("targetName:{},methodName:{},params:{}",targetName,methodName,params);
        }

        startTime = System.currentTimeMillis();
        logMessage.setStartTime(DateUtil.now());
        logMessage.setDescription(loggable.value());
        //session中获取用户名
        //logMessage.setOperateName(request.getSession().getAttribute("user").toString());
        logMessage.setUri(request.getRequestURI());
        logMessage.setIp(request.getRemoteHost());
        logMessage.setUrl(request.getRequestURL().toString());
    }


    private void handleResponseLog(LogMessage logMessage, Loggable loggable, Object result) {
        endTime = System.currentTimeMillis();
        Long duration = endTime - startTime;
        logMessage.setDuration(duration);
        logMessage.setEndTime(DateUtil.now());
        //是否保存到MongoDB
        if(loggable.save()){
            logMessageService.save(logMessage);
        }
        if(loggable.console()){
            log.info("方法执行所需时间 : {} , 输出的结果 : {}",duration,result);
        }
    }

    private static Map<String, Object> getMethodParamNames(ProceedingJoinPoint point) {
        String classType = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        // 参数值
        Object[] args = point.getArgs();
            Class<?>[] classes = new Class[args.length];
        for (int k = 0; k < args.length; k++) {
            if (!args[k].getClass().isPrimitive()) {
                // 获取的是封装类型而不是基础类型
                String result = args[k].getClass().getName();
                Class s = map.get(result);
                classes[k] = s == null ? args[k].getClass() : s;
            }
        }
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        // 获取指定的方法，第二个参数可以不传，但是为了防止有重载的现象，还是需要传入参数的类型
        Method method = null;
        try {
            method = Class.forName(classType).getMethod(methodName, classes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 参数名
        String[] parameterNames = pnd.getParameterNames(method);
        // 通过map封装参数和参数值
        HashMap<String, Object> paramMap = new HashMap();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }

    private static HashMap<String, Class> map = new HashMap<String, Class>() {
        {
            put("java.lang.Integer", int.class);
            put("java.lang.Double", double.class);
            put("java.lang.Float", float.class);
            put("java.lang.Long", long.class);
            put("java.lang.Short", short.class);
            put("java.lang.Boolean", boolean.class);
            put("java.lang.Char", char.class);
        }
    };
}
