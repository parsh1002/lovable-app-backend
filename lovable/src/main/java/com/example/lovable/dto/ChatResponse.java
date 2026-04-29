package com.example.lovable.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
public class ChatResponse {
    List<MessageResponse> messages;
    LocalDateTime nextCursor;
}
