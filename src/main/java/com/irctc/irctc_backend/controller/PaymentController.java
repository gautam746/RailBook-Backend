package com.irctc.irctc_backend.controller;

import com.irctc.irctc_backend.dto.PaymentRequest;
import com.irctc.irctc_backend.entity.Payment;
import com.irctc.irctc_backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/pay")
    public Payment processPayment(@RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }

    @GetMapping("/status/{pnr}")
    public Payment getPaymentStatus(@PathVariable String pnr) {
        return paymentService.getPaymentByPnr(pnr);
    }
}