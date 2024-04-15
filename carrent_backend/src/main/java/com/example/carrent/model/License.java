package com.example.carrent.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Driver_License")
public class License {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long licenseNumber;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    private String image;

    // Getters
    public Long getId() {
        return id;
    }

    public Long getLicenseNumber() {
        return licenseNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getImage() {
        return image;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setLicenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
