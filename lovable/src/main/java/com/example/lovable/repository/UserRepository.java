package com.example.lovable.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import com.example.lovable.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String userName);

    Optional<User> findByUsername(String userName);

    boolean existsByEmail(String email);

    Page<User> findByIdNot(UUID Id, Pageable pageable);


}
