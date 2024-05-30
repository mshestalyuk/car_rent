package com.example.carrent.repository;

import com.example.carrent.model.License;
import com.example.carrent.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    @Query("SELECT l FROM License l JOIN UserDetails ud ON l.id = ud.driverLicense.id WHERE ud.user.id = :userId")
    License findByUserId(@Param("userId") Long userId);
}
