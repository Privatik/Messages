package com.io.messages.config;

import com.io.messages.controler.MessageController;
import com.io.messages.handler.WebSocketHandler;
import com.io.messages.repo.MessageRepo;
import com.io.messages.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MessageRepo messageRepo;

    @Autowired
    public WebSocketConfig(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(WebSocketHandler.getWebSocketHandler(messageRepo),"/messages-update");
    }
}
