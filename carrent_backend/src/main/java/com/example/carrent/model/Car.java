package com.example.carrent.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car", schema = "public")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;

    @Column(nullable = false)
    private String fuel;

    @Column(nullable = false)
    private String transmission;

    @Column(nullable = false)
    private String consumption;

    @Column(nullable = false)
    private Integer seats;

    @Column(nullable = false)
    private String luggageCapacity;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Double price;

    // Getters and setters
    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
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

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
