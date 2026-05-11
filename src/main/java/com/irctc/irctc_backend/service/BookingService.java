package com.irctc.irctc_backend.service;

import com.irctc.irctc_backend.dto.BookingRequest;
import com.irctc.irctc_backend.dto.PassengerRequest;
import com.irctc.irctc_backend.entity.*;
import com.irctc.irctc_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TrainRepository trainRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Transactional  // ✅ booking + passengers ek transaction mein save honge
    public Booking bookTicket(BookingRequest request) {

        Train train = trainRepository.findById(request.getTrainId()).orElseThrow();
        User user = userRepository.findById(request.getUserId()).orElseThrow();

        if (train.getAvailableSeats() < request.getPassengers().size()) {
            throw new RuntimeException("Not enough seats available");
        }

        Booking booking = new Booking();
        booking.setTrain(train);
        booking.setUser(user);
        booking.setPnr(UUID.randomUUID().toString());

        booking = bookingRepository.save(booking);

        for (PassengerRequest p : request.getPassengers()) {
            Passenger passenger = new Passenger();
            passenger.setName(p.getName());
            passenger.setAge(p.getAge());
            passenger.setGender(p.getGender());
            passenger.setBooking(booking);
            passengerRepository.save(passenger);
        }

        train.setAvailableSeats(train.getAvailableSeats() - request.getPassengers().size());
        trainRepository.save(train);

        return booking;
    }

    // ✅ sirf read hai - @Transactional ki zaroorat nahi
    public Booking getBookingByPnr(String pnr) {
        return bookingRepository.findByPnr(pnr)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Transactional  // ✅ multiple deletes hain - ek transaction mein hone chahiye
    public String cancelBooking(String pnr) {

        Booking booking = bookingRepository.findByPnr(pnr)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Train train = booking.getTrain();

        List<Passenger> passengers = passengerRepository.findByBookingId(booking.getId());

        int passengerCount = passengers.size();

        // seats wapas add
        train.setAvailableSeats(train.getAvailableSeats() + passengerCount);
        trainRepository.save(train);

        // passengers delete
        passengerRepository.deleteAll(passengers);

        // booking delete
        bookingRepository.delete(booking);

        return "Ticket cancelled successfully";
    }

    // ✅ sirf read hai - @Transactional ki zaroorat nahi
    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}