package com.plf.learn.jwt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Panlf
 * @date 2019/5/16
 */
@Component
public class RedisUtils {
    @Resource
    public  StringRedisTemplate stringRedisTemplate;

    /**
     * 往Redis中存储字符串数据
     * @param key 键
     * @param value 值
     * @param time 过期时间，单位秒
     */
    public void insertStr(String key,String value,Integer time){
        stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    /**
     * 根据key从Redis获取值
     * @param key 键
     * @return
     */
    public String getStr(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

}
