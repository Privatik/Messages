package com.io.messages.controler;

import com.io.messages.domain.Message;
import com.io.messages.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MessageController {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
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

    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public Message greeting(Message message) throws Exception {
        Thread.sleep(3000);
        System.out.println(message.getText());
         return  messageRepo.save(message);
    }
}
