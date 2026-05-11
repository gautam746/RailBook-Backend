package com.irctc.irctc_backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pnr;

    @ManyToOne
    private User user;

    @ManyToOne
    private Train train;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Passenger> passengers;

    public Booking() {}

    public Long getId() {
        return id;
    }

    public String getPnr() {
        return pnr;
    }

    public User getUser() {
        return user;
    }

    public Train getTrain() {
        return train;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}