package com.io.messages.controler;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String name) {
        return "Hello, " + name + "!";
    }
}
