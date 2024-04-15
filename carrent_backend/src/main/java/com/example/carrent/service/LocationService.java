package com.example.carrent.service;

import com.example.carrent.dto.LocationDTO;
import com.example.carrent.model.Location;
import com.example.carrent.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDTO> findAllLocations() {
        return locationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LocationDTO findLocationById(Long id) {
        return locationRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public LocationDTO createLocation(LocationDTO locationDTO) {
        Location location = convertToEntity(locationDTO);
        location = locationRepository.save(location);
        return convertToDto(location);
    }

    public LocationDTO updateLocation(Long id, LocationDTO locationDTO) {
        return locationRepository.findById(id)
                .map(existingLocation -> {
                    // Update the existing location with the DTO values
                    // Make sure to add null checks and proper validation
                    existingLocation.setAddress(locationDTO.getAddress());
                    existingLocation.setPostalCode(locationDTO.getPostalCode());
                    existingLocation.setCity(locationDTO.getCity());
                    existingLocation.setCounty(locationDTO.getCounty());
                    return convertToDto(locationRepository.save(existingLocation));
                })
                .orElse(null);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    private LocationDTO convertToDto(Location location) {
        // Conversion logic
        return new LocationDTO();
    }

    private Location convertToEntity(LocationDTO locationDTO) {
        // Conversion logic
        return new Location();
    }
}
