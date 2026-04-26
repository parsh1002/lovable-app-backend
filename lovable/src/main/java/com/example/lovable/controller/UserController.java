package com.example.lovable.controller;

import com.example.lovable.dto.UserResponse;
import com.example.lovable.entity.User;
import com.example.lovable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Scanner;

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
//        User user = userRepository.findAll().get(0);
        return new UserResponse(user.getEmail(), user.getUsername(), user.getId());

    }
    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {

        User currentUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
//        User currentUser = userRepository.findAll().get(0);

        return userRepository.findAll()
                .stream()
                .filter(user -> !user.getId().equals(currentUser.getId())) // exclude self
                .map(user -> new UserResponse(user.getEmail(),  user.getUsername(),user.getId()))
                .toList();
    }
}
