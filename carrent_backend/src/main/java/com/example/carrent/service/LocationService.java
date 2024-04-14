package com.example.carrent.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import com.example.carrent.dto.LocationDTO;

@Service
public class LocationService {
    private final ConcurrentHashMap<Long, LocationDTO> locations = new ConcurrentHashMap<>();
    private long locationIdSequence = 1L;  // Example location ID sequence generator

    public List<LocationDTO> findAllLocations() {
        return new ArrayList<>(locations.values());
    }

    public LocationDTO findLocationById(Long id) {
        return locations.get(id);
    }

    public LocationDTO addLocation(LocationDTO locationDTO) {
        locationDTO.setId(locationIdSequence++);
        locations.put(locationDTO.getId(), locationDTO);
        return locationDTO;
    }

    public LocationDTO updateLocation(Long id, LocationDTO locationDTO) {
        if (locations.containsKey(id)) {
            locationDTO.setId(id);
            locations.put(id, locationDTO);
            return locationDTO;
        }
        return null;  // Or throw an exception if preferred
    }

    public void deleteLocation(Long id) {
        locations.remove(id);
    }
}
