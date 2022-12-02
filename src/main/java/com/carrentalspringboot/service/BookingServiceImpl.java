package com.carrentalspringboot.service;

import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.model.User;
import com.carrentalspringboot.repository.BookingRepository;
import com.carrentalspringboot.specifications.BookingSpecification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findAll(
                BookingSpecification
                        .builder()
                        .user(user)
                        .build());
    }


    @Override
    public Booking getBooking(Car car, User user, LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findByCarAndAndUserAndStartDateAndEndDate(car, user, startDate, endDate);
    }

    @Override
    public List<Booking> getBookings() {
        List<Booking> bookings = new ArrayList<Booking>();
        bookingRepository.findAll().forEach(bookingToAdd -> bookings.add(bookingToAdd));
        return bookings;
    }
}
