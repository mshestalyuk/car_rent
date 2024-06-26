package com.example.carrent.dto;

public class CarDTO {
    private Long idCar; // Adjusting field name to match the Car entity
    private String fuel;
    private String transmission;
    private String consumption;
    private int seats;
    private String luggageCapacity;
    private String image;
    private String model; // Added model field
    private Double price; // Added price field with Double to accommodate decimal values

    // Getters and Setters
    public Long getId() {
        return idCar;
    }

    public void setId(Long idCar) {
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
