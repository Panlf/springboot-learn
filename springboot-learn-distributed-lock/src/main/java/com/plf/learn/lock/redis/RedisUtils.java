package com.plf.learn.lock.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Panlf
 * @date 2019/5/20
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
     * 往Redis中存储字符串数据 无过期时间
     * @param key
     * @param value
     */
    public void insertStr(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
    }

    /**
     * 往Redis中存储字符串数据，存在则不插入
     * @param key 键
     * @param value 值
     */
    public void insertIfAbsent(String key,String value){
        stringRedisTemplate.opsForValue().setIfAbsent(key,value);
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
