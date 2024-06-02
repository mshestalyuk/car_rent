package com.example.carrent.service;

import com.example.carrent.dto.UserDetailsDTO;
import com.example.carrent.model.License;
import com.example.carrent.model.User;
import com.example.carrent.model.UserDetails;
import com.example.carrent.repository.LicenseRepository;
import com.example.carrent.repository.UserDetailsRepository;
import com.example.carrent.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    private UserRepository userRepository;  // Add this
    private LicenseRepository licenseRepository;


    public UserDetailsDTO getUserDetailsByUserId(Long userId) {
        Optional<UserDetails> userDetails = userDetailsRepository.findByUserId(userId);
        return userDetails.map(this::convertToDTO).orElse(null);
    }
    
    public UserDetailsDTO findUserDetailsByDriverLicenseId(Long licenseId) {
        return userDetailsRepository.findByDriverLicenseId(licenseId)
            .map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("No UserDetails found for License ID: " + licenseId));
    }

    
    public UserDetailsDTO createUserDetails(Long userId, UserDetailsDTO userDetailsDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    
        License license = null;
        if (userDetailsDTO.getDriverLicenseId() != null) {
            license = licenseRepository.findById(userDetailsDTO.getDriverLicenseId())
                      .orElseThrow(() -> new RuntimeException("License not found with id: " + userDetailsDTO.getDriverLicenseId()));
        }
    
        UserDetails userDetails = new UserDetails();
        userDetails.setUser(user);
        userDetails.setDriverLicense(license); // Can be null if not provided
        userDetails.setName(userDetailsDTO.getName());
        userDetails.setSurname(userDetailsDTO.getSurname());
        userDetails.setLocation(userDetailsDTO.getLocation());
        userDetails.setImage(userDetailsDTO.getImage());
    
        UserDetails savedUserDetails = userDetailsRepository.save(userDetails);
        return convertToDTO(savedUserDetails);
    }
    
    
    

    private UserDetailsDTO convertToDTO(UserDetails userDetails) {
        UserDetailsDTO dto = new UserDetailsDTO();
        dto.setUserId(userDetails.getUserId());
        dto.setName(userDetails.getName());
        dto.setSurname(userDetails.getSurname());
        dto.setLocation(userDetails.getLocation());
        dto.setImage(userDetails.getImage());
    
        // Set the driver license ID if available
        if (userDetails.getDriverLicense() != null) {
            dto.setDriverLicenseId(userDetails.getDriverLicense().getId());
        }
        return dto;
    }
    
}
