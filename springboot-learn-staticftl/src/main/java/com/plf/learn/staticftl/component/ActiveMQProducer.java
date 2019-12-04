package com.plf.learn.staticftl.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Panlf
 * @date 2019/11/18
 */
@Component
public class ActiveMQProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     *
     * @param destination   队列
     * @param message   信息
     */
    public void sendMessage(String destination,String message){
        jmsMessagingTemplate.convertAndSend(destination,message);
    }
}
