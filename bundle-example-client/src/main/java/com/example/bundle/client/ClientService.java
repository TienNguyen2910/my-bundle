package com.example.bundle.client;

import com.example.bundle.common.Booking;

import java.util.List;

public interface ClientService {
    List<Booking> bookings();
    void addBooking(Booking booking);
}
