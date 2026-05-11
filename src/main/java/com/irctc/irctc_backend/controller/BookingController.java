package com.irctc.irctc_backend.controller;

import com.irctc.irctc_backend.dto.BookingRequest;
import com.irctc.irctc_backend.entity.Booking;
import com.irctc.irctc_backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public Booking bookTicket(@RequestBody BookingRequest request){
        return bookingService.bookTicket(request);
    }

    @GetMapping("/pnr/{pnr}")
    public Booking getBookingByPnr(@PathVariable String pnr){
        return bookingService.getBookingByPnr(pnr);
    }

    @DeleteMapping("/cancel/{pnr}")
    public String cancelBooking(@PathVariable String pnr){
        return bookingService.cancelBooking(pnr);
    }
    @GetMapping("/user/{userId}")
    public List<Booking> getUserBookings(@PathVariable Long userId){
        return bookingService.getUserBookings(userId);
    }
}