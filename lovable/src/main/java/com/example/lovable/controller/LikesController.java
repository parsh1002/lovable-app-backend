package com.example.lovable.controller;

import com.example.lovable.config.Securityconfig;
import com.example.lovable.entity.User;
import com.example.lovable.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesController {

    private final LikesService likesService;

    @PostMapping("user/{userId}")
    public String likeUser(@PathVariable UUID userId){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return likesService.likeUser(user, userId);
    }
    @PostMapping("/test")
    public String test() {
        return "LIKE WORKS";
    }
}
