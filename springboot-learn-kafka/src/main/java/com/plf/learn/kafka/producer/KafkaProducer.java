package com.plf.learn.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plf.learn.kafka.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Panlf
 * @date 2019/12/26
 */
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    /**
     * kafka发送消息，这里只能使用String
     * @param key
     * @param message
     */
    public void sendMessage(String key,Message message){
        ObjectMapper mapper = new ObjectMapper();
        String jsonMessage="";
        try{
            jsonMessage = mapper.writeValueAsString(message);
        }catch (Exception e){
            e.printStackTrace();
        }
        kafkaTemplate.send(message.getTopic(),message.getPartition(),key,jsonMessage);
    }

}
