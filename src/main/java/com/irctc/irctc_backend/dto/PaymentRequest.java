package com.irctc.irctc_backend.dto;

public class PaymentRequest {

    private String pnr;
    private String paymentMethod; // "CARD", "UPI", "NETBANKING"
    private Double amount;

    public String getPnr() { return pnr; }
    public void setPnr(String pnr) { this.pnr = pnr; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}