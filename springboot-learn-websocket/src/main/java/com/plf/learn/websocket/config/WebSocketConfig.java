package com.plf.learn.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @EnableWebSocketMessageBroker
 *  用于开启使用STOMP协议来传输基于代理(MessageBroker)的消息
 * @author Panlf
 * @date 2020/3/20
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //设置广播节点
        registry.enableStompBrokerRelay("/topic", "/user");
        //定义一对一推送的前缀
        registry.setUserDestinationPrefix("/user/");
        //定义websocket前缀
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个Stomp的节点（endpoint）,并指定使用SockJS协议
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}
