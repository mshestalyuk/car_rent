package com.example.carrent.dto;

public class UserDetailsDTO {
    private Long userId;
    private String name;
    private String surname;
    private String location;
    private String image;
    private Long driverLicenseId; // Corrected field name

    // Constructors, Getters, and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getDriverLicenseId() {
        return driverLicenseId;
    }

    public void setDriverLicenseId(Long driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
    }
}
