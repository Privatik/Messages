package com.io.messages.stor;

import com.io.messages.domain.Chat;

public class ChatStor {

    private Chat chat;

    public ChatStor(Chat chat) {
        this.chat = chat;
    }


    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void plusUser()
    {
        chat.setCount(chat.getCount() + 1);
    }

    public void minusUser()
    {
        chat.setCount(chat.getCount() - 1);
    }
}
