package com.example.lovable.controller;


import com.example.lovable.dto.ChatResponse;
import com.example.lovable.dto.MessageResponse;
import com.example.lovable.entity.Message;
import com.example.lovable.entity.User;
import com.example.lovable.repository.UserRepository;
import com.example.lovable.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserRepository userRepository;

    @PostMapping("/{receiverId}")
    public String sendMessage(@PathVariable UUID receiverId, @RequestBody String content){

        User sender = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return messageService.sendMessage(sender, receiverId, content);
    }

    @GetMapping("/{userId}")
    public ChatResponse getChat(
            @PathVariable UUID userId,
            @RequestParam(required = false) LocalDateTime cursor,
            @RequestParam(defaultValue = "20") int size
                                         ){
        if (size > 50) {
            throw new RuntimeException("Page size too large");
        }

        User current = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return messageService.getChat(current, userId, cursor, size);
    }
}
