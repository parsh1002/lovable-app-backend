package com.example.lovable.service;


import com.example.lovable.entity.Likes;
import com.example.lovable.entity.User;
import com.example.lovable.repository.LikesRepository;
import com.example.lovable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final UserRepository userRepository;
    private final LikesRepository likesRepository;

    public String likeUser(User currentUser, UUID likedUserId){

        User likedUser = userRepository.findById(likedUserId).orElseThrow(() -> new RuntimeException("User Not Found"));

        if(likesRepository.existsByUserAndLikedUser(currentUser, likedUser)){
            return "ALready Liked";
        }
        Likes like = new Likes();
        like.setUser(currentUser);
        like.setLikedUser(likedUser);
        return "User Liked Successfully!";
    }
}
