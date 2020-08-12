package com.io.messages.handler;

import com.io.messages.adapter.DataAdapter;
import com.io.messages.domain.Message;
import com.io.messages.repo.MessageRepo;
import com.io.messages.repo.UserRepo;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.apache.coyote.Adapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class WebSocketHandler extends AbstractWebSocketHandler {

    private static WebSocketHandler webSocketHandler;
    private MessageRepo messageRepo;
    private JsonAdapter<Message> adapter;
    private Set<WebSocketSession> clients;

    public WebSocketHandler(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;

        Moshi moshi = new Moshi.Builder()
                .add(new DataAdapter())
                .build();
        adapter = moshi.adapter(Message.class);
        clients = new HashSet<>();
    }

    public static WebSocketHandler getWebSocketHandler(MessageRepo messageRepo)
    {
        if (webSocketHandler == null)
        {
           webSocketHandler = new WebSocketHandler(messageRepo);
        }
        return webSocketHandler;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
            clients.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
            clients.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Message messagePayload = adapter.fromJson(message.getPayload());
        synchronized (this) {
        messagePayload.setDateTime(LocalDateTime.now());
        messageRepo.save(messagePayload);
            clients.forEach(it ->
            {
                try {
                    it.sendMessage(new TextMessage(adapter.toJson(messagePayload)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        session.sendMessage(new TextMessage(exception.getMessage()));
    }
}
