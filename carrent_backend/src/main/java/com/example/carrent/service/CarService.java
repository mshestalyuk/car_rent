package com.example.carrent.service;

import com.example.carrent.dto.CarDTO;
import com.example.carrent.model.Car;
import com.example.carrent.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> findAllCars() {
        return carRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CarDTO findCarById(Long id) {
        return carRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public CarDTO addCar(CarDTO carDTO) {
        Car car = convertToEntity(carDTO);
        Car savedCar = carRepository.save(car);
        return convertToDto(savedCar);
    }

    public CarDTO updateCar(Long id, CarDTO carDTO) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setFuel(carDTO.getFuel());
                    car.setTransmission(carDTO.getTransmission());
                    car.setConsumption(carDTO.getConsumption());
                    car.setSeats(carDTO.getSeats());
                    car.setLuggageCapacity(carDTO.getLuggageCapacity());
                    car.setImage(carDTO.getImage());
                    carRepository.save(car);
                    return convertToDto(car);
                })
                .orElse(null);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    private CarDTO convertToDto(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getIdCar());
        dto.setFuel(car.getFuel());
        dto.setTransmission(car.getTransmission());
        dto.setConsumption(car.getConsumption());
        dto.setSeats(car.getSeats());
        dto.setLuggageCapacity(car.getLuggageCapacity());
        dto.setImage(car.getImage());
        dto.setPrice(car.getPrice());
        dto.setModel(car.getModel());
        return dto;
    }

    private Car convertToEntity(CarDTO dto) {
        Car car = new Car();
        car.setFuel(dto.getFuel());
        car.setTransmission(dto.getTransmission());
        car.setConsumption(dto.getConsumption());
        car.setSeats(dto.getSeats());
        car.setLuggageCapacity(dto.getLuggageCapacity());
        car.setImage(dto.getImage());
        car.setPrice(car.getPrice());
        car.setModel(car.getModel());
        return car;
    }
}
