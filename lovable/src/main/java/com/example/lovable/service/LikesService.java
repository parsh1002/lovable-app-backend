package com.example.lovable.service;


import com.example.lovable.entity.Likes;
import com.example.lovable.entity.Matches;
import com.example.lovable.entity.User;
import com.example.lovable.repository.LikesRepository;
import com.example.lovable.repository.MatchRepository;
import com.example.lovable.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final UserRepository userRepository;
    private final LikesRepository likesRepository;
    private final MatchRepository matchRepository;

    public String likeUser(User currentUser, UUID likedUserId){

        User likedUser = userRepository.findById(likedUserId).orElseThrow(() -> new RuntimeException("User Not Found"));

        if(currentUser.getId().equals(likedUserId)) throw new RuntimeException("You cannot lie yourself!");

        if(likesRepository.existsByUserAndLikedUser(currentUser, likedUser)){
            return "ALready Liked";
        }
        Likes like = new Likes();
        like.setUser(currentUser);
        like.setLikedUser(likedUser);
        likesRepository.save(like);


        boolean isMatch = likesRepository.existsByUserAndLikedUser(likedUser, currentUser);

        if(isMatch){
            User user1 = currentUser.getId().compareTo(likedUser.getId()) < 0 ? currentUser : likedUser;
            User user2 = currentUser.getId().compareTo(likedUser.getId()) < 0 ? likedUser : currentUser;

            Matches match = new Matches();
            match.setUser1(user1);
            match.setUser2(user2);

            matchRepository.save(match);

            return "Its a MATCH!!";


        }
        return "User Liked Successfully!";
    }
}
