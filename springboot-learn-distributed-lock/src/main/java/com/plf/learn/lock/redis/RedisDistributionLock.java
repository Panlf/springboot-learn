package com.plf.learn.lock.redis;

import com.plf.learn.lock.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


/**
 * @author Panlf
 * @date 2019/5/17
 */
@Slf4j
@Component
public class RedisDistributionLock {

    /**
     *  锁的过期时间
     */
    private final static long LOCK_EXPIRE_TIME = 30L;

    @Resource
    public  StringRedisTemplate stringRedisTemplate;

    /**
     *  加锁
     * @param key 键
     * @param value 值
     * @return 是否加锁成功
     */
    public boolean tryLock(String key,String value) {
        //如果没有键，则设置键值，表示加锁成功 同时设置过期时间，防止死锁

        /**
         *  这里根据方法说明是可以返回boolean的，但是根据测试，发现就算执行成功，也是返回NULL的，
         *  但是如果setIfAbsent不加过期时间则是可以返回boolean，目前问题还不清楚。
         *  Boolean setIfAbsent(K key, V value, long timeout, TimeUnit unit) 莫名其妙不管是否执行成功只能返回null，
         *  所以只能退而求次了
         *  建议Redis实现分布式锁使用Redisson方式
         */
        if(stringRedisTemplate.opsForValue().setIfAbsent(key,value)){
            stringRedisTemplate.expire(key,LOCK_EXPIRE_TIME,TimeUnit.SECONDS);
            return true;
        }

        //如果有键，获取锁的有效期间
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        //如果锁过期了
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            //获取上一个锁的时间value 并设置新值  防止并发
            String oldValue =stringRedisTemplate.opsForValue().getAndSet(key,value);
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue) ){
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key 键
     * @param value 值
     */
    public void unlock(String key,String value){
        if(StringUtils.isNotEmpty(key)){
            String oldValue = stringRedisTemplate.opsForValue().get(key);
            if(oldValue.equals(value)){
                stringRedisTemplate.delete(key);
            }
        }
    }
}
