package com.irctc.irctc_backend.repository;

import com.irctc.irctc_backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByPnr(String pnr);

    List<Booking> findByUserId(Long userId);

}