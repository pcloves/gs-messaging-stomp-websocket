package com.example.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class GreetingController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public GreetingController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/privateChat")
    public void privateChat(ChatMessage message, Principal principal) {

        message.setFromUser(principal.getName());

        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/topic/privateChat", message);
        simpMessagingTemplate.convertAndSendToUser(message.getToUser(), "/topic/privateChat", message);
    }

    @MessageMapping("/roomChat")
    public void roomChat(ChatMessage message, Principal principal) {
        simpMessagingTemplate.convertAndSend("/topic/roomChat", message);
    }
}
