package com.plf.learn.custom.event.configuration;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Panlf
 * @date 2019/4/19
 */
@EnableAsync
@Configuration
public class ThreadPool implements AsyncConfigurer {
    @Nullable
    @Override
    @Bean("taskExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 线程池创建时候初始化的线程数
        executor.setCorePoolSize(10);
        // 线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(20);
        // 用来缓冲执行任务的队列
        executor.setQueueCapacity(200);
        // 允许线程的空闲时间60秒
        executor.setKeepAliveSeconds(60);
        // 线程池名的前缀
        executor.setThreadNamePrefix("taskExecutor-");
        // 线程池对拒绝任务的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Nullable
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
