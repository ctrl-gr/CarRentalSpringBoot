package com.carrentalspringboot.service;

import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.model.User;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    void saveBooking(Booking booking);

    void updateBooking(Booking booking);

    void deleteBooking(Booking booking);

    List<Booking> getBookingsByUser(User user);

    Booking getBooking(Car car, User user, LocalDate startDate, LocalDate endDate);

    List<Booking> getBookings();
}
