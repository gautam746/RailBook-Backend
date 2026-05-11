package com.irctc.irctc_backend.repository;

import com.irctc.irctc_backend.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    List<Passenger> findByBookingId(Long bookingId);

}