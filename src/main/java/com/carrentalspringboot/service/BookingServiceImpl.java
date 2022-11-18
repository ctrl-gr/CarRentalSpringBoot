package com.carrentalspringboot.service;

import java.util.ArrayList;
import java.util.List;
import com.carrentalspringboot.model.User;
import com.carrentalspringboot.model.Booking;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking getBookingById(int id) {
        return bookingRepository.findById(id).get();
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
    public List<Booking> getBookings() {
        List<Booking> bookings = new ArrayList<Booking>();
        bookingRepository.findAll().forEach(bookingToAdd -> bookings.add(bookingToAdd));
        return bookings;
    }
}
