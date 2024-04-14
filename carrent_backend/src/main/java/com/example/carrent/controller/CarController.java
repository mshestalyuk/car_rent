package com.example.carrent.controller;

import com.example.carrent.dto.CarDTO;
import com.example.carrent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/")
    public List<CarDTO> getAllCars() {
        return carService.findAllCars();
    }

    @GetMapping("/{id}")
    public CarDTO getCarById(@PathVariable Long id) {
        return carService.findCarById(id);
    }

    @PostMapping("/")
    public CarDTO addCar(@RequestBody CarDTO carDTO) {
        return carService.addCar(carDTO);
    }

    @PutMapping("/{id}")
    public CarDTO updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        return carService.updateCar(id, carDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
