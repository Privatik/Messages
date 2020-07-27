package com.io.messages.controler;


import com.io.messages.domain.Message;
import com.io.messages.domain.User;
import com.io.messages.exception.NotFoundException;
import com.io.messages.handler.WebSocketHandler;
import com.io.messages.repo.MessageRepo;
import com.io.messages.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class MessageController implements IFoundTwoElement<Message, User>{
        private final MessageRepo messageRepo;
        private final UserRepo userRepo;

        @Autowired
        public MessageController(MessageRepo messageRepo, UserRepo userRepo) {
             this.messageRepo = messageRepo;
             this.userRepo = userRepo;
        }

        @GetMapping("/message")
        public List<Message> getListMessage()
        {
            return messageRepo.findAll();
        }

        @GetMapping("/message/{id}")
        public Message getMessage(@PathVariable Long id)
        {
            return foundElement(id);
        }

        @PostMapping("/message/{id}")
        public Message postMessage(@PathVariable Long id,@RequestBody Message message)
        {
            message.setUser(foundTwoElement(id));
            message.setDateTime(LocalDateTime.now());
            // chat.setMessages(new ArrayList<>());
            //   chat.setUsers(new HashSet<>());
            return messageRepo.save(message);
        }

        @PutMapping("/message/{id}")
        public Message putMessage(@PathVariable Long id, @RequestBody Message message)
        {
            Message messageFromOb = foundElement(id);

            BeanUtils.copyProperties(message, messageFromOb, "id", "text", "dateTime");
            return messageRepo.save(messageFromOb);
        }

        @DeleteMapping("/message/{id}")
        public void deleteMessage(@PathVariable Long id)
        {
            messageRepo.deleteById(id);
        }

        @DeleteMapping("/message/del")
        public void deleteAllMessage()
        {
            messageRepo.deleteAll();
        }

    @Override
    public User foundTwoElement(Long id) {
       return userRepo.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
        public Message foundElement(Long id) {
            return messageRepo.findById(id)
                    .orElseThrow(NotFoundException::new);
        }
}
