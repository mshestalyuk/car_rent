package com.example.carrent.service;

import com.example.carrent.dto.LicenseDTO;
import com.example.carrent.model.License;
import com.example.carrent.model.UserDetails;
import com.example.carrent.repository.LicenseRepository;
import com.example.carrent.repository.UserDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseService {

    private final LicenseRepository licenseRepository;
    private UserDetailsRepository userDetailsRepository;


    @Autowired
    public LicenseService(LicenseRepository licenseRepository, UserDetailsRepository userDetailsRepository) {
        this.licenseRepository = licenseRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    public List<LicenseDTO> findAllLicenses() {
        return licenseRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LicenseDTO findLicenseById(Long id) {
        return licenseRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public LicenseDTO createLicense(LicenseDTO licenseDTO) {
        License license = convertToEntity(licenseDTO);
        license = licenseRepository.save(license);
        return convertToDto(license);
    }

    public LicenseDTO updateLicense(Long id, LicenseDTO licenseDTO) {
        return licenseRepository.findById(id)
                .map(existingLicense -> {
                    // Update the existing license with DTO values
                    existingLicense.setLicenseNumber(licenseDTO.getLicenseNumber());
                    existingLicense.setStartDate(licenseDTO.getStartDate());
                    existingLicense.setExpirationDate(licenseDTO.getExpirationDate());
                    existingLicense.setImage(licenseDTO.getImage());
                    return convertToDto(licenseRepository.save(existingLicense));
                })
                .orElse(null);
    }

    public void deleteLicense(Long id) {
        licenseRepository.deleteById(id);
    }

    private LicenseDTO convertToDto(License license) {
        LicenseDTO licenseDTO = new LicenseDTO();
        licenseDTO.setId(license.getId());
        licenseDTO.setLicenseNumber(license.getLicenseNumber());
        licenseDTO.setStartDate(license.getStartDate());
        licenseDTO.setExpirationDate(license.getExpirationDate());
        licenseDTO.setImage(license.getImage());
        return licenseDTO;
    }
    

    private License convertToEntity(LicenseDTO licenseDTO) {
        License license = new License();
        license.setId(licenseDTO.getId());  // Typically not set for new entities
        license.setLicenseNumber(licenseDTO.getLicenseNumber());
        license.setStartDate(licenseDTO.getStartDate());
        license.setExpirationDate(licenseDTO.getExpirationDate());
        license.setImage(licenseDTO.getImage());
        return license;
    }
    


    public LicenseDTO findLicenseByUserId(Long userId) {
        return userDetailsRepository.findByUserId(userId)
            .map(UserDetails::getDriverLicense)
            .map(this::convertToDto)
            .orElse(null);
    }
}
