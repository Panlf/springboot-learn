package com.plf.learn.websocket.service;

import com.plf.learn.websocket.entity.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Panlf
 * @date 2020/3/20
 */
@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * 广播
     * 发给所有在线用户
     *
     * @param msg
     */
    public void sendMsg(ResponseMessage msg) {
        simpMessagingTemplate.convertAndSend("/topic", msg);
    }

    /**
     * 发送给指定用户
     * @param users
     * @param msg
     */
    public void send2Users(List<String> users, ResponseMessage msg) {
        users.forEach(userName -> {
            simpMessagingTemplate.convertAndSendToUser(userName, "/msg", msg);
        });
    }
}
