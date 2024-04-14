package com.example.carrent.controller;

import com.example.carrent.dto.BookingDTO;
import com.example.carrent.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    public List<BookingDTO> getAllBookings() {
        return bookingService.findAllBookings();
    }

    @GetMapping("/{id}")
    public BookingDTO getBookingById(@PathVariable Long id) {
        return bookingService.findBookingById(id);
    }

    @PostMapping("/")
    public BookingDTO addBooking(@RequestBody BookingDTO bookingDTO) {
        return bookingService.addBooking(bookingDTO);
    }

    @PutMapping("/{id}")
    public BookingDTO updateBooking(@PathVariable Long id, @RequestBody BookingDTO bookingDTO) {
        return bookingService.updateBooking(id, bookingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
