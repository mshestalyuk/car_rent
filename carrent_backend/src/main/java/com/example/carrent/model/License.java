package com.example.carrent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "driver_license", schema = "public")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long licenseNumber;

    @Column(nullable = false)
    private java.util.Date startDate;

    @Column(nullable = false)
    private java.util.Date expirationDate;

    @Column(nullable = false)
    private String image;

    // Constructors, getters, and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }

    public java.util.Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(java.util.Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
