package com.example.lovable.controller;


import com.example.lovable.dto.AuthResponse;
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

    @PostMapping("/login")
    public AuthResponse Login(@RequestBody LoginRequest request){


        String token = userService.Login(request.getEmail(), request.getPassword());

        return new AuthResponse(token);
    }
}
