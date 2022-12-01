package com.carrentalspringboot.service;

import java.util.List;

import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.model.User;

public interface BookingService {

    void saveBooking(Booking booking);

    void updateBooking(Booking booking);

    void deleteBooking(Booking booking);

    List<Booking> getBookingsByUser(User user);

    List<Booking> getBookings();
}
