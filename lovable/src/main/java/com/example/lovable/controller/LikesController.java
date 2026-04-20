package com.example.lovable.controller;

import com.example.lovable.config.Securityconfig;
import com.example.lovable.entity.User;
import com.example.lovable.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesController {

    private final LikesService likesService;

    @PostMapping("/user_Id")
    public String likeUser(@PathVariable UUID user_Id){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return likesService.likeUser(user, user_Id);
    }
}
