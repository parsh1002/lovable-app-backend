package com.example.lovable.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver", nullable = false)
    private User receiver;

    private String content;

    private LocalDateTime timestamp;

    @PrePersist
    public void onCreate(){
        timestamp = LocalDateTime.now();
    }


}
