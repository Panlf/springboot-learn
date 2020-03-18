package com.plf.learn.log.service;

import com.plf.learn.log.entity.LogMessage;
import com.plf.learn.log.repository.LogMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Panlf
 * @date 2020/3/18
 */
@Service
public class LogMessageService {
    @Autowired
    private LogMessageRepository logMessageRepository;

    public void save(LogMessage logMessage){
        logMessageRepository.save(logMessage);
    }
}
