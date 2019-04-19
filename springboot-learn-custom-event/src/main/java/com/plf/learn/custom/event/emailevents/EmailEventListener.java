package com.plf.learn.custom.event.emailevents;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Panlf
 * @date 2019/4/19
 */
@Component
@Slf4j
public class EmailEventListener implements ApplicationListener<EmailEvent>{

    /**
     * 异步处理监听到邮件
     * @param event
     */
    @Async
    @Override
    public void onApplicationEvent(EmailEvent event) {
        log.info("监听到事件--邮箱地址:" + event.getEmailAddress());

        //模拟逻辑处理
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("异常:{}",e.getMessage());
        }

        log.info("事件处理完成");
    }
}
