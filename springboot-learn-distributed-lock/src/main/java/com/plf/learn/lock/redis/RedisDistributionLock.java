package com.plf.learn.lock.redis;

import com.plf.learn.lock.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * @author Panlf
 * @date 2019/5/17
 */
@Slf4j
@Component
public class RedisDistributionLock {

    private final static long LOCK_TRY_INTERVAL = 30L;

    private final static long LOCK_TIMEOUT = 20 * 1000L;

    /**
     *  锁的过期时间
     */
    private final static long LOCK_EXPIRE_TIME = 30 * 1000L;

    @Resource
    public  StringRedisTemplate stringRedisTemplate;

    public boolean tryLock(String key,String value) {
        if(stringRedisTemplate.opsForValue().setIfAbsent(key,value,LOCK_EXPIRE_TIME,TimeUnit.MILLISECONDS)){//对应setnx命令
            //可以成功设置,也就是key不存在
            return true;
        }

        //判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        //如果锁过期
        if(!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){//currentValue不为空且小于当前时间
            //获取上一个锁的时间value
            String oldValue =stringRedisTemplate.opsForValue().getAndSet(key,value);//对应getset，如果key存在

            //假设两个线程同时进来这里，因为key被占用了，而且锁过期了。获取的值currentValue=A(get取的旧的值肯定是一样的),两个线程的value都是B,key都是K.锁时间已经过期了。
            //而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的value已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if(!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue) ){
                //oldValue不为空且oldValue等于currentValue，也就是校验是不是上个对应的商品时间戳，也是防止并发
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
