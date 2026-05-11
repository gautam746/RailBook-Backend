package com.irctc.irctc_backend.service;

import com.irctc.irctc_backend.dto.PaymentRequest;
import com.irctc.irctc_backend.entity.Booking;
import com.irctc.irctc_backend.entity.Payment;
import com.irctc.irctc_backend.repository.BookingRepository;
import com.irctc.irctc_backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Payment processPayment(PaymentRequest request) {

        // PNR valid hai ya nahi check karo
        Booking booking = bookingRepository.findByPnr(request.getPnr())
                .orElseThrow(() -> new RuntimeException("Booking not found for PNR: " + request.getPnr()));

        // Pehle se payment ho chuki hai?
        paymentRepository.findByPnr(request.getPnr()).ifPresent(p -> {
            if ("SUCCESS".equals(p.getStatus())) {
                throw new RuntimeException("Payment already done for this PNR");
            }
        });

        Payment payment = new Payment();
        payment.setPnr(request.getPnr());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());

        // Simulate karo - 90% chance success (realistic feel)
        boolean isSuccess = Math.random() > 0.1;

        if (isSuccess) {
            payment.setStatus("SUCCESS");
            payment.setTransactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            payment.setPaidAt(new java.util.Date().toString());

        } else {
            payment.setStatus("FAILED");
            payment.setTransactionId(null);
        }

        return paymentRepository.save(payment);
    }

    public Payment getPaymentByPnr(String pnr) {
        return paymentRepository.findByPnr(pnr)
                .orElseThrow(() -> new RuntimeException("Payment not found for PNR: " + pnr));
    }
}