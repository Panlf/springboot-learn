package com.plf.learn.custom.event.emailevents;

import org.springframework.context.ApplicationEvent;

/**
 * @author Panlf
 * @date 2019/4/19
 */
public class EmailEvent extends ApplicationEvent {

    private String emailAddress;

    public EmailEvent(String emailAddress) {
        super(emailAddress);
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
