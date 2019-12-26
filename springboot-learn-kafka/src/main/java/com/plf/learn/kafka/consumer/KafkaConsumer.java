package com.plf.learn.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author Panlf
 * @date 2019/12/26
 */
@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "kafka-test")
    public void receive(ConsumerRecord<?, ?> consumer){
        log.info("topic name:{},key:{},partition:{},offset:{},message:{}",
                consumer.topic(),consumer.key(),
                consumer.partition(),consumer.offset(),consumer.value());
    }
}
