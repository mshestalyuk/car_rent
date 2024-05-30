package com.example.carrent.repository;

import com.example.carrent.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByUserId(Long userId);
    Optional<UserDetails> findByDriverLicenseId(Long id);  // Corrected method name
}
