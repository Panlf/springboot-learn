package com.plf.learn.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Panlf
 * @date 2019/12/26
 */
@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 往Redis中存储字符串数据
     * @param key 键
     * @param value 值
     * @param time 过期时间，单位秒
     */
    public void set(String key,String value,Integer time){
        stringRedisTemplate.opsForValue().set(key,value,time, TimeUnit.SECONDS);
    }

    /**
     * 根据key从Redis获取值
     * @param key 键
     * @return
     */
    public String get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exits(String key){
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 删除key
     * @param key
     * @return 删除失败返回false，其他返回true
     */
    public boolean remove(String key){
        if(exits(key)){
           return stringRedisTemplate.delete(key);
        }
        return true;
    }
}

