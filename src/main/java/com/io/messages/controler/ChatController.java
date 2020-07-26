package com.io.messages.controler;

import com.io.messages.domain.Chat;
import com.io.messages.domain.User;
import com.io.messages.exception.NotFoundException;
import com.io.messages.repo.ChatRepo;
import com.io.messages.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class ChatController implements IFound<Chat>{
    private final ChatRepo chatRepo;

    @Autowired
    public ChatController(ChatRepo chatRepo) {
        this.chatRepo = chatRepo;
    }

    @GetMapping("/chat")
    public List<Chat> getListChat()
    {
        return chatRepo.findAll();
    }

    @GetMapping("/chat/{id}")
    public Chat getChat(@PathVariable Long id)
    {
        return foundElement(id);
    }

    @PostMapping("/chat")
    public Chat postChat(@RequestBody Chat chat)
    {
        if (chat.getCount() == null) {
            chat.setCount(0L);
        }
       // chat.setMessages(new ArrayList<>());
     //   chat.setUsers(new HashSet<>());
        return chatRepo.save(chat);
    }

    @PutMapping("/chat/{id}")
    public Chat putChat(@PathVariable Long id, @RequestBody Chat chat)
    {
        Chat chatFromOb = foundElement(id);

        BeanUtils.copyProperties(chat, chatFromOb, "id");
        return chatRepo.save(chatFromOb);
    }

    @DeleteMapping("/chat/{id}")
    public void deleteChat(@PathVariable Long id)
    {
        chatRepo.deleteById(id);
    }

    @DeleteMapping("/chat/del")
    public void deleteAllChat()
    {
        chatRepo.deleteAll();
    }

    @Override
    public Chat foundElement(Long id) {
        return chatRepo.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
