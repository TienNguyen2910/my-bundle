package com.example.bundle.provider;

import com.example.bundle.common.Booking;
import com.example.bundle.common.BookingService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BookingServiceImpl implements BookingService {
    private Map<Long, Booking> bookings = new HashMap<>();
    @Override
    public List<Booking> list() {
        return new LinkedList<>(bookings.values());
    }

    @Override
    public Booking get(Long id) {
        return bookings.get(id);
    }

    @Override
    public void add(Booking booking) {
        bookings.put(booking.getId(),booking);
    }
}
