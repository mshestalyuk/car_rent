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
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            final String jwt = jwtUtil.generateToken(authentication);

            return ResponseEntity.ok(new AuthResponse(jwt));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }
    }

}
