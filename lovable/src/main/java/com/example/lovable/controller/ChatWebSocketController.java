package com.example.lovable.controller;

import com.example.lovable.dto.ChatMessage;
import com.example.lovable.entity.User;
import com.example.lovable.repository.UserRepository;
import com.example.lovable.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;
    private final UserRepository userRepository;

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessage chatMessage, Principal principal){

        User sender = (User)((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        messageService.sendMessage(
                sender,
                chatMessage.getReceieverId(),
                chatMessage.getContent()
        );
        simpMessagingTemplate.convertAndSendToUser(
                chatMessage.getReceieverId().toString(),
                "/queue/messages",
                chatMessage
        );

    }

}
