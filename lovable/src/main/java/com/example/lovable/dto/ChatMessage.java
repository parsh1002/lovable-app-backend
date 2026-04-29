package com.example.lovable.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ChatMessage {
    private UUID userId;
    private UUID receieverId;
    private String content;
}
