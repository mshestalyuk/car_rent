package com.example.carrent.controller;

import com.example.carrent.dto.LocationDTO;
import com.example.carrent.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/")
    public List<LocationDTO> getAllLocations() {
        return locationService.findAllLocations();
    }

    @GetMapping("/{id}")
    public LocationDTO getLocationById(@PathVariable Long id) {
        return locationService.findLocationById(id);
    }

    @PostMapping("/")
    public LocationDTO addLocation(@RequestBody LocationDTO locationDTO) {
        return locationService.addLocation(locationDTO);
    }

    @PutMapping("/{id}")
    public LocationDTO updateLocation(@PathVariable Long id, @RequestBody LocationDTO locationDTO) {
        return locationService.updateLocation(id, locationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }
}
