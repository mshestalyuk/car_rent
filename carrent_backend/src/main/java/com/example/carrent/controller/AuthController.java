package com.example.carrent.controller;

import com.example.carrent.dto.AuthRequest;
import com.example.carrent.dto.AuthResponse;
import com.example.carrent.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    JwtUtil jwtUtil;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/api/v1/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
            // Perform authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            // Generate JWT using the entire Authentication object
            final String jwt = jwtUtil.generateToken(authentication);

            // Return the JWT in the response
            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (AuthenticationException e) {
            // Handle exception for authentication failure
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }

    // Define classes AuthRequest and AuthResponse as static inner classes or in their own file
    // Ensure they contain the necessary fields for your authentication process, e.g., username, password for AuthRequest and JWT token for AuthResponse
}
