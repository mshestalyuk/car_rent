package com.example.carrent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "\"car\"") // Note the quotes around the table name
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_car;

    private String fuel;
    private String transmission;
    private String consumption;
    private int seats;
    private String luggageCapacity;
    private String image;

    // Getters and setters
    public Long getIdCar() { 
        return id_car;
    }

    public void setIdCar(Long id_car) {
        this.id_car = id_car;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getConsumption() {
        return consumption;
    }

    public void setConsumption(String consumption) {
        this.consumption = consumption;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getLuggageCapacity() {
        return luggageCapacity;
    }

    public void setLuggageCapacity(String luggageCapacity) {
        this.luggageCapacity = luggageCapacity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
