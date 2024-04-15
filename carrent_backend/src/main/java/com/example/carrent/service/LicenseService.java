package com.example.carrent.service;

import com.example.carrent.dto.LicenseDTO;
import com.example.carrent.model.License;
import com.example.carrent.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseService {

    private final LicenseRepository licenseRepository;

    @Autowired
    public LicenseService(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
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
        // Conversion logic
        return new LicenseDTO();
    }

    private License convertToEntity(LicenseDTO licenseDTO) {
        // Conversion logic
        return new License();
    }
}
