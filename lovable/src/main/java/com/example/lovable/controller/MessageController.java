package com.example.lovable.controller;


import com.example.lovable.entity.Message;
import com.example.lovable.entity.User;
import com.example.lovable.repository.MatchRepository;
import com.example.lovable.repository.MessageRepository;
import com.example.lovable.repository.UserRepository;
import com.example.lovable.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserRepository userRepository;

    @PostMapping("/{receieverId}")
    public String sendMessage(@PathVariable UUID receiverId, @RequestBody String content){
        User sender = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return messageService.sendMessage(sender, receiverId, content);
    }

    @GetMapping("/{userId}")
    public List<Message> getChat(@PathVariable UUID userId){
        User current = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal()
                ;

        return messageService.getChat(current, userId);
    }
}
