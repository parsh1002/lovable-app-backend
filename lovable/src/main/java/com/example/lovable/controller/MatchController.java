package com.example.lovable.controller;

import com.example.lovable.entity.Matches;
import com.example.lovable.entity.User;
import com.example.lovable.repository.MatchRepository;
import com.example.lovable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    @GetMapping
    public List<Matches> getMatches(){
//        String email = (String) SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getPrincipal();
//
//        User user = userRepository.findByEmail(email)
//                .orElseThrow();
        User user = userRepository.findAll().get(0);
        return matchRepository.findByUser1OrUser2(user, user);


    }
}
