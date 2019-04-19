package com.plf.learn.custom.event.emailevents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Panlf
 * @date 2019/4/19
 */
@Component
public class EmailEventPublish {
    @Autowired
    private ApplicationContext applicationContext;

    public void publishEvent(String emailAddress) {
        EmailEvent event = new EmailEvent(emailAddress);
        applicationContext.publishEvent(event);
    }
}
