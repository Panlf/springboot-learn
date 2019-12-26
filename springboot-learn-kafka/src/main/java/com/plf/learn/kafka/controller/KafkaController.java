package com.plf.learn.kafka.controller;

import com.plf.learn.kafka.message.Message;
import com.plf.learn.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

/**
 * @author Panlf
 * @date 2019/12/26
 */
@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/send/{id}/{partition}/{content}")
    public String sendMessage(@PathVariable Integer id,@PathVariable Integer partition,@PathVariable String content){
        Message message = new Message();
        message.setId(id);
        message.setTopic("kafka-test");
        message.setContent(content);
        message.setPartition(partition);
        message.setCreateTime(new Date());
        kafkaProducer.sendMessage(String.valueOf(id),message);
        return "success";
    }
}
