package com.plf.learn.websocket.controller;

import com.plf.learn.websocket.entity.RequestMessage;
import com.plf.learn.websocket.entity.ResponseMessage;
import com.plf.learn.websocket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author Panlf
 * @date 2020/3/20
 */
@Controller
@Slf4j
public class WebSocketController {
    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/welcome")//@MessageMapping和@RequestMapping功能类似，用于设置URL映射地址，浏览器向服务器发起请求，需要通过该地址。
    @SendTo("/topic/welcome")//如果服务器接受到了消息，就会对订阅了@SendTo括号中的地址传送消息。
    public ResponseMessage say(RequestMessage message) throws Exception {
        log.info("获取到信息 : {}",message.getName());
        List<String> users = Lists.newArrayList();
        users.add("d892bf12bf7d11e793b69c5c8e6f60fb");//此处写死只是为了方便测试,此值需要对应页面中订阅个人消息的userId
        webSocketService.send2Users(users, new ResponseMessage("admin hello"));

        return new ResponseMessage("Welcome, " + message.getName() + "!");
    }

    @GetMapping("index")
    public String toHtml(){
        return "ws";
    }
}
