package com.plf.learn.log.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解作用于方法级别且运行时起作用
 * @author Panlf
 * @date 2020/3/18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {

    /**
     * 日志信息描述
     * @return
     */
    String value() default "";


    /**
     * 是否保存到数据库
     * @return
     */
    boolean save() default true;

    /**
     * 是否输出到控制台
     * @return
     */
    boolean console() default true;

}
