package com.larionov.epam.entity;

import java.time.LocalDateTime;

public class Reservation {
    private String hotel;
    private LocalDateTime date;

    public Reservation(String hotel, LocalDateTime date) {
        this.hotel = hotel;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "hotel='" + hotel + '\'' +
                ", date=" + date +
                '}';
    }
}
