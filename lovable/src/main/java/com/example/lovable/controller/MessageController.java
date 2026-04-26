package com.example.lovable.controller;


import com.example.lovable.dto.MessageResponse;
import com.example.lovable.entity.Message;
import com.example.lovable.entity.User;
import com.example.lovable.repository.UserRepository;
import com.example.lovable.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserRepository userRepository;

    @PostMapping("/send/{receiverId}")
    public String sendMessage(@PathVariable UUID receiverId, @RequestBody String content){
        User sender = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return messageService.sendMessage(sender, receiverId, content);
    }

    @GetMapping("getchat/{userId}")
    public List<MessageResponse> getChat(@PathVariable UUID userId){
        User current = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();


        return messageService.getChat(current, userId);
    }
}
