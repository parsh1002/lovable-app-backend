package com.example.lovable.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TestTable {
    @Id
    private Long id;
}