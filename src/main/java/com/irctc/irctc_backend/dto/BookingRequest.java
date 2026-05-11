package com.irctc.irctc_backend.dto;

import java.util.List;

public class BookingRequest {

    private Long userId;
    private Long trainId;
    private List<PassengerRequest> passengers;

    public Long getUserId() {
        return userId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public List<PassengerRequest> getPassengers() {
        return passengers;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public void setPassengers(List<PassengerRequest> passengers) {
        this.passengers = passengers;
    }
}