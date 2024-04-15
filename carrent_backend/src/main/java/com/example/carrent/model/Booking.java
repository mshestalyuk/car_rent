package com.example.carrent.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long booking_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pick_up_loc_id")
    private Location pickUpLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drop_off_loc_id")
    private Location dropOffLocation;

    @Column(name = "pick_up_date")
    @Temporal(TemporalType.TIMESTAMP) // Change to TIMESTAMP if you also want to track time
    private Date pickUpDate;

    @Column(name = "drop_off_date")
    @Temporal(TemporalType.TIMESTAMP) // Change to TIMESTAMP if you also want to track time
    private Date dropOffDate;

    private String status;

    // Getters
    public Long getBooking_id() {
        return booking_id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public Location getPickUpLocation() {
        return pickUpLocation;
    }

    public Location getDropOffLocation() {
        return dropOffLocation;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public Date getDropOffDate() {
        return dropOffDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setBooking_id(Long booking_id) {
        this.booking_id = booking_id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setPickUpLocation(Location pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public void setDropOffLocation(Location dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
