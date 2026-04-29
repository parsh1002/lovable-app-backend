package com.example.lovable.dto;

import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
public class MessageResponse {
    private UUID receiverId;
    private UUID senderId;
    private String content;
    private LocalDateTime sentAt;



    @PrePersist
    public void onCreate(){
        sentAt = LocalDateTime.now();
    }
}
