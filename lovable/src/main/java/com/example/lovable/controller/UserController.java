package com.example.lovable.controller;

import com.example.lovable.dto.UserResponse;
import com.example.lovable.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/me")
    public UserResponse getCurrentUser(){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new UserResponse(user.getEmail(), user.getUsername());


    }
}
