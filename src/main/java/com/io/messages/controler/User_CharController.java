package com.io.messages.controler;

import com.io.messages.domain.Chat;
import com.io.messages.domain.User;
import com.io.messages.exception.NotFoundException;
import com.io.messages.repo.ChatRepo;
import com.io.messages.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class User_CharController implements IFoundTwoElement<User, Chat>{
    private final ChatRepo chatRepo;
    private final UserRepo userRepo;

    @Autowired
    public User_CharController(ChatRepo chatRepo, UserRepo userRepo) {
        this.chatRepo = chatRepo;
        this.userRepo = userRepo;
    }

    @PutMapping("/user/{id_us}/chat/{id_chat}")
    public User putCharOfUser(@PathVariable Long id_us, @PathVariable Long id_chat)
    {
        User user = foundElement(id_us);
        Chat chat = foundTwoElement(id_chat);

        user.getChatList().add(chat);
     //   chat.getUsers().add(user);
        chat.setCount(chat.getCount() + 1);

        chatRepo.save(chat);
        return userRepo.save(user);
    }

    @Override
    public Chat foundTwoElement(Long id) {
        return chatRepo.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public User foundElement(Long id) {
        return userRepo.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
