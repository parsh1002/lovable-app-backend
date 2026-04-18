package com.example.lovable.controller;


import com.example.lovable.dto.LoginRequest;
import com.example.lovable.entity.User;
import com.example.lovable.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public User Register(@RequestBody User user){
        return userService.register(user);
    }

    @GetMapping("/login")
    public User Login(@RequestBody LoginRequest request){
        return userService.Login(request.getEmail(), request.getPassword());
    }
}
