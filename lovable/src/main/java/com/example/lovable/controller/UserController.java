package com.example.lovable.controller;

import com.example.lovable.dto.UserResponse;
import com.example.lovable.entity.User;
import com.example.lovable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static tools.jackson.databind.jsonFormatVisitors.JsonValueFormat.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserResponse getCurrentUser(){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return new UserResponse(user.getEmail(), user.getUsername(), user.getId());

    }
    @GetMapping("/users")
    public Page getAllUsers(Pageable pageable) {
        if (pageable.getPageSize() > 50) {
            throw new RuntimeException("Page size too large");
        }

        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return userRepository.findByIdNot(currentUser.getId(), pageable)
                .map(user -> new UserResponse(user.getEmail(),  user.getUsername(),user.getId()))
                ;
    }
}
