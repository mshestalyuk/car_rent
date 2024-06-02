package com.example.carrent.dto;

import java.util.Date;

public class BookingDTO {
    private Long bookingId;
    private Long userId;
    private Long carId;
    private Long pickUpLocId;
    private Long dropOffLocId;
    private Date pickUpDate;
    private Date pickOffDate;
    private Date dropOffDate;
    private String status;

    // Getters and Setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getPickUpLocId() {
        return pickUpLocId;
    }

    public void setPickUpLocId(Long pickUpLocId) {
        this.pickUpLocId = pickUpLocId;
    }

    public Long getDropOffLocId() {
        return dropOffLocId;
    }

    public void setDropOffLocId(Long dropOffLocId) {
        this.dropOffLocId = dropOffLocId;
    }

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }


    public Date getPickOffDate() {
        return pickOffDate;
    }

    public void setPickOffDate(Date pickOffDate) {
        this.pickOffDate = pickOffDate;
    }
    public Date getDropOffDate() {
        return dropOffDate;
    }

    public void setDropOffDate(Date dropOffDate) {
        this.dropOffDate = dropOffDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
