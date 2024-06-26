package com.example.carrent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_details", schema = "public")
public class UserDetails {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Maps the userId to the User entity's ID
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_driver") 
    private License driverLicense;

    @Column()
    private String name;

    @Column()
    private String surname;

    @Column()
    private String location;

    @Column()
    private String image;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public License getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(License driverLicense) {
        this.driverLicense = driverLicense;
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
}