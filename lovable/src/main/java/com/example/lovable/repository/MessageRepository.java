package com.example.lovable.repository;


import com.example.lovable.entity.Message;
import com.example.lovable.entity.User;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    Page<Message> findBySenderAndReceiverOrReceiverAndSenderOrderByTimestampDesc(
            User sender, User Receiver,
            User Receiver2, User sender2,
            Pageable pageable
    );

    @Query("""
            select m from Mesages m
            where
            ((m.sender = :user1 AND m.receiever = :user2)
            OR
            (m.sender = :user2 AND m.receiever = :user1)
            AND (:cursor IS NULL OR m.timestamp < :cursor)
            ORDER BY m.timestamp DESC
            """)
    Page<Message> findChatMessages(
            @Param("user1") User user1,
            @Param("user2") User user2,
            @Param("cursor") LocalDateTime cursor,
            Pageable pageable
            );
}
