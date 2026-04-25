package com.example.lovable.repository;


import com.example.lovable.entity.Message;
import com.example.lovable.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findBySenderAndReceiverOrReceiverAndSenderOrderByTimestampAsc(
            User sender, User Receiver,
            User Receiver2, User sender2
    );

}
