package com.example.lovable.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(
        name = "lovable_user_data",
        indexes = {
                @Index(name = "idx_username", columnList = "username"),
                @Index(name = "idx_email", columnList = "email")
        }
)

public class User {


    // Basic Identification Data
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    // User Profile
    private String firstName;
    private String lastName;
    private String bio;
    private String profilePictureUrl;


    // Audit of timestamps
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }

    private LocalDateTime updatedAt;
    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

    // Role
    @Enumerated(EnumType.STRING)
    private Role role;


    // Security and Auth
    private boolean isEnabled = true;
    private boolean isAccountNonLocked = true;
    private int numberOfFailedAttempts;
    private LocalDateTime lastLoginAt;



}
