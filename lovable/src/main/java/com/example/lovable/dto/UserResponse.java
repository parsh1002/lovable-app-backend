package com.example.lovable.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Data
@AllArgsConstructor
public class UserResponse {
    private String email;
    private String username;
    private UUID Id;


}
