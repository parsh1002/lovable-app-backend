package com.example.lovable.repository;

import com.example.lovable.entity.Likes;
import com.example.lovable.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    Optional<Likes> findByUserAndLikedUser(User user, User likedUser);

    boolean existsByUserAndLikedUser(User user, User likedUser);
}
