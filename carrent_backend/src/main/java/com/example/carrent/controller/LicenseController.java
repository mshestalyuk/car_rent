package com.example.carrent.controller;

import com.example.carrent.dto.LicenseDTO;
import com.example.carrent.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/licenses")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @GetMapping("/")
    public List<LicenseDTO> getAllLicenses() {
        return licenseService.findAllLicenses();
    }

    @GetMapping("/{id}")
    public LicenseDTO getLicenseById(@PathVariable Long id) {
        return licenseService.findLicenseById(id);
    }

    @PostMapping("/")
    public LicenseDTO addLicense(@RequestBody LicenseDTO licenseDTO) {
        return licenseService.addLicense(licenseDTO);
    }

    @PutMapping("/{id}")
    public LicenseDTO updateLicense(@PathVariable Long id, @RequestBody LicenseDTO licenseDTO) {
        return licenseService.updateLicense(id, licenseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLicense(@PathVariable Long id) {
        licenseService.deleteLicense(id);
    }
}
