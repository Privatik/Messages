package com.io.messages.model;

import com.io.messages.domain.Message;

public class SendMessage {
    private int sendId;
    private Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }
}
