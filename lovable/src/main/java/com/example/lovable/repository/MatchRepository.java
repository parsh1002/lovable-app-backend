package com.example.lovable.repository;

import com.example.lovable.entity.Matches;
import com.example.lovable.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Matches, UUID> {

    List<Matches> findByUser1OrUser2(User user1, User user2);
}
