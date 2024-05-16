package com.example.carrent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings", schema = "public")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pick_up_loc_id", nullable = false)
    private Location pickUpLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drop_off_loc_id", nullable = false)
    private Location dropOffLocation;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date pickUpDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date pickOffDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dropOffDate;

    // Constructors, getters, and setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(Location pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public java.util.Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(java.util.Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public java.util.Date getPickOffDate() {
        return pickOffDate;
    }

    public void setPickOffDate(java.util.Date pickOffDate) {
        this.pickOffDate = pickOffDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(java.util.Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }
}
