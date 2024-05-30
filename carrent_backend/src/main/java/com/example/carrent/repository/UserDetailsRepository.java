package com.example.carrent.repository;

import com.example.carrent.model.UserDetails;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    // Example: Find UserDetails by name
    UserDetails findByName(String name);
    Optional<UserDetails> findByUserId(Long userId);
}
