package com.example.carrent.controller;

import com.example.carrent.dto.LicenseDTO;
import com.example.carrent.dto.UserDetailsDTO;
import com.example.carrent.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-details")
public class UserDetailsController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsDTO> getUserDetailsByUserId(@PathVariable Long userId) {
        UserDetailsDTO userDetails = userDetailsService.getUserDetailsByUserId(userId);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createUserDetails(@PathVariable Long userId, @RequestBody UserDetailsDTO userDetailsDTO) {
        try {
            UserDetailsDTO newUserDetails = userDetailsService.createUserDetails(userId, userDetailsDTO);
            return ResponseEntity.ok(newUserDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
