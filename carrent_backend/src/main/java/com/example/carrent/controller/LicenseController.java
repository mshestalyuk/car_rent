package com.example.carrent.controller;

import com.example.carrent.dto.LicenseDTO;
import com.example.carrent.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/licenses")
public class LicenseController {

    private final LicenseService licenseService;

    @Autowired
    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    public ResponseEntity<List<LicenseDTO>> getAllLicenses() {
        List<LicenseDTO> licenses = licenseService.findAllLicenses();
        return ResponseEntity.ok(licenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LicenseDTO> getLicenseById(@PathVariable Long id) {
        LicenseDTO license = licenseService.findLicenseById(id);
        return license != null ? ResponseEntity.ok(license) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LicenseDTO> createLicense(@RequestBody LicenseDTO licenseDTO) {
        LicenseDTO newLicense = licenseService.createLicense(licenseDTO);
        return ResponseEntity.ok(newLicense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LicenseDTO> updateLicense(@PathVariable Long id, @RequestBody LicenseDTO licenseDTO) {
        LicenseDTO updatedLicense = licenseService.updateLicense(id, licenseDTO);
        return updatedLicense != null ? ResponseEntity.ok(updatedLicense) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLicense(@PathVariable Long id) {
        licenseService.deleteLicense(id);
        return ResponseEntity.ok().build();
    }
}
