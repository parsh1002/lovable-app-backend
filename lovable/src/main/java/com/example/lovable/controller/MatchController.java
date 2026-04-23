package com.example.lovable.controller;

import com.example.lovable.entity.Matches;
import com.example.lovable.entity.User;
import com.example.lovable.repository.MatchRepository;
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

    @GetMapping
    public List<Matches> getMatches(){
        User user = (User)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return matchRepository.findByUser1OrUser2(user, user);


    }
}
