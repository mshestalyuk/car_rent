package com.example.carrent.service;

import com.example.carrent.dto.BookingDTO;
import com.example.carrent.model.Booking;
import com.example.carrent.repository.BookingRepository;
import com.example.carrent.repository.CarRepository;
import com.example.carrent.repository.LocationRepository;
import com.example.carrent.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final LocationRepository locationRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          CarRepository carRepository,
                          LocationRepository locationRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.locationRepository = locationRepository;
    }

    public List<BookingDTO> findAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BookingDTO findBookingById(Long id) {
        return bookingRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);  // Consider adding custom exception handling here
    }

    public BookingDTO addBooking(BookingDTO bookingDTO) {
        Booking booking = convertToEntity(bookingDTO);
        Booking savedBooking = bookingRepository.save(booking);
        return convertToDto(savedBooking);
    }

    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        return bookingRepository.findById(id)
                .map(existingBooking -> {
                    updateExistingBooking(existingBooking, bookingDTO);
                    bookingRepository.save(existingBooking);
                    return convertToDto(existingBooking);
                }).orElse(null);  // Consider adding custom exception handling here
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    private void updateExistingBooking(Booking booking, BookingDTO dto) {
        booking.setUser(userRepository.findById(dto.getUserId()).orElse(null));
        booking.setCar(carRepository.findById(dto.getCarId()).orElse(null));
        booking.setPickUpLocation(locationRepository.findById(dto.getPickUpLocId()).orElse(null));
        booking.setDropOffLocation(locationRepository.findById(dto.getDropOffLocId()).orElse(null));
        booking.setPickUpDate(dto.getPickUpDate());
        booking.setDropOffDate(dto.getDropOffDate());
        booking.setPickOffDate(dto.getPickOffDate());
        booking.setStatus(dto.getStatus());
    }

    private BookingDTO convertToDto(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setBookingId(booking.getBookingId());
        dto.setUserId(booking.getUser().getId());
        dto.setCarId(booking.getCar().getIdCar());
        dto.setPickUpLocId(booking.getPickUpLocation().getId());
        dto.setDropOffLocId(booking.getDropOffLocation().getId());
        dto.setPickUpDate(booking.getPickUpDate());
        dto.setDropOffDate(booking.getDropOffDate());
        dto.setPickOffDate(booking.getPickOffDate());
        dto.setStatus(booking.getStatus());
        return dto;
    }
    
    private Booking convertToEntity(BookingDTO dto) {
        Booking booking = new Booking();
        if (dto.getBookingId() != null) {  // Conditionally set the ID only for updates
            booking.setBookingId(dto.getBookingId());
        }
        booking.setUser(userRepository.findById(dto.getUserId()).orElse(null));
        booking.setCar(carRepository.findById(dto.getCarId()).orElse(null));
        booking.setPickUpLocation(locationRepository.findById(dto.getPickUpLocId()).orElse(null));
        booking.setDropOffLocation(locationRepository.findById(dto.getDropOffLocId()).orElse(null));
        booking.setPickUpDate(dto.getPickUpDate());
        booking.setDropOffDate(dto.getDropOffDate());
        booking.setPickOffDate(dto.getPickOffDate());
        booking.setStatus("pending");
        return booking;
    }

    public List<BookingDTO> findBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(this::convertToDto)  // Correct method name here
                .collect(Collectors.toList());
    }
    
    
}
