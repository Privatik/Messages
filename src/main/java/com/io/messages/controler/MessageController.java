package com.io.messages.controler;

import com.io.messages.domain.Message;
import com.io.messages.model.MessageText;
import com.io.messages.repo.MessageRepo;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/base")
public class MessageController {
    private final MessageRepo messageRepo;
    private final JsonAdapter<Message> adapter;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;

        Moshi moshi = new Moshi.Builder().build();
        adapter = moshi.adapter(Message.class);
    }

    @GetMapping
    public List<Message> getList()
    {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getMessage(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    public Message postMessage(@RequestBody Message message)
    {
        System.out.println(message.getName());
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message putMessage(@PathVariable("id") Message messageFromDB , @RequestBody Message message)
    {
        BeanUtils.copyProperties(message, messageFromDB,"id");

        return messageRepo.save(messageFromDB);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Message message)
    {
       messageRepo.delete(message);
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message greetings(Message message) {
        return messageRepo.save(message);
    }
}
