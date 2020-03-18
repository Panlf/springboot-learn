package com.plf.learn.log.repository;

import com.plf.learn.log.entity.LogMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Panlf
 * @date 2020/3/18
 */
public interface LogMessageRepository extends MongoRepository<LogMessage,String> {
}
