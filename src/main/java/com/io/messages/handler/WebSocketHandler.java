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
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.time.LocalDateTime;

public class WebSocketHandler extends AbstractWebSocketHandler {

    private static WebSocketHandler webSocketHandler;
    private MessageRepo messageRepo;
    private JsonAdapter<Message> adapter;

    public WebSocketHandler(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;

        Moshi moshi = new Moshi.Builder()
                .add(new DataAdapter())
                .build();
        adapter = moshi.adapter(Message.class);
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
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Message messagePayload = adapter.fromJson(message.getPayload());
        messagePayload.setDateTime(LocalDateTime.now());
        messageRepo.save(messagePayload);
        session.sendMessage(new TextMessage(adapter.toJson(messagePayload)));
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        session.sendMessage(new TextMessage(exception.getMessage()));
    }
}
