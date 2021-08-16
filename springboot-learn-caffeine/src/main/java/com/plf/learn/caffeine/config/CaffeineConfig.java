package com.plf.learn.caffeine.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author panlf
 * @date 2021/8/16
 */
@Configuration
@EnableCaching //开启缓存
public class CaffeineConfig {

    //配置Caffeine缓存行为（例如到期，缓存大小限制等）
    public Caffeine caffeineConfig() {

        /*
        initialCapacity=[integer]: 初始的缓存空间大小
        maximumSize=[long]: 缓存的最大条数
        maximumWeight=[long]: 缓存的最大权重
        expireAfterAccess=[duration]: 最后一次写入或访问后经过固定时间过期
        expireAfterWrite=[duration]: 最后一次写入后经过固定时间过期
        refreshAfterWrite=[duration]: 创建缓存或者最近一次更新缓存后经过固定的时间间隔，刷新缓存
        recordStats：开发统计功能

        expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准。
        maximumSize和maximumWeight不可以同时使用
         */
        Caffeine caffeine = Caffeine.newBuilder()
                //最后一次写入后经过固定时间过期
                .expireAfterWrite(60, TimeUnit.MINUTES)
                //初始的缓存空间大小
                .initialCapacity(100)
                //缓存的最大条数
                .maximumSize(1000);
        return caffeine;
    }

    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeineConfig());
        return caffeineCacheManager;
    }
}
