package com.example.carrent.service;

import com.example.carrent.dto.UserDetailsDTO;
import com.example.carrent.model.UserDetails;
import com.example.carrent.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDetailsDTO getUserDetailsByUserId(Long userId) {
        Optional<UserDetails> userDetails = userDetailsRepository.findByUserId(userId);
        return userDetails.map(this::convertToDTO).orElse(null);
    }

    private UserDetailsDTO convertToDTO(UserDetails userDetails) {
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setUserId(userDetails.getUserId());
        dto.setName(userDetails.getName());
        dto.setSurname(userDetails.getSurname());
        dto.setLocation(userDetails.getLocation());
        dto.setImage(userDetails.getImage());;
        return dto;
    }
}
