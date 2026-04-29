package com.example.lovable.controller;

import com.example.lovable.repository.UserRepository;
import com.example.lovable.service.OnlineStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/online/")
public class OnlineController {

    private final OnlineStatusService onlineStatusService;
    private final UserRepository userRepository;

    @GetMapping("/{user}")
    public boolean isOnline(@PathVariable String user){
        return onlineStatusService.isOnline(user);
    }

}
