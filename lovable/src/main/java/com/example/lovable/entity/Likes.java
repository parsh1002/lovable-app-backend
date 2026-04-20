package com.example.lovable.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "Likes_data",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "liked_user_id"})
        }
)
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

//    Who liked
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

//    Who was liked
    @ManyToOne
    @JoinColumn(name = "liked_user_id", nullable = false)
    private User likedUser;

//    When was the action performed
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate(){
        createdAt = LocalDateTime.now();
    }



}
