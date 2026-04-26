package com.example.lovable.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserResponse {
    private String email;
    private String username;
    private UUID Id;


}
