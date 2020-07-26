package com.io.messages.servise;

import com.io.messages.controler.IFound;
import com.io.messages.domain.Chat;
import com.io.messages.domain.User;
import com.io.messages.exception.NotFoundException;
import com.io.messages.repo.ChatRepo;
import com.io.messages.repo.UserRepo;
import com.io.messages.stor.ChatStor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
public class UserAndChatService {
    private final ChatRepo chatRepo;
    private final UserRepo userRepo;

    @Autowired
    public UserAndChatService(ChatRepo chatRepo, UserRepo userRepo) {
        this.chatRepo = chatRepo;
        this.userRepo = userRepo;
    }

    public User putFirstChat(User user)
    {
        Chat chatFromOb = chatRepo.getFirst();
        if (chatFromOb == null)
        {
            chatFromOb = new Chat();
            chatFromOb.setCount(1L);
            chatFromOb.setMessages(new ArrayList<>());
            chatFromOb.setUsers(new HashSet<>());
        }
        else
        {
            chatFromOb.setCount(chatFromOb.getCount() + 1);
        }
        chatFromOb.getUsers().add(user);
        chatRepo.save(chatFromOb);

        user.getChatList().add(chatFromOb);
        return userRepo.save(user);
    }

}
