package com.plf.learn.custom.event.controller;

import com.plf.learn.custom.event.emailevents.EmailEventPublish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Panlf
 * @date 2019/4/19
 */
@RestController
@Slf4j
public class MailEventController {

    @Autowired
    private EmailEventPublish emailEventPublish;

    @RequestMapping("/event")
    public void publishEvent(@RequestParam String emailAddress) {
        // 发布事件 -- 采用异步处理
        emailEventPublish.publishEvent(emailAddress);

        // 正常该语句先执行
        log.info("Controller业务处理");
    }
}
