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
    public void saveBooking(String licensePlate, String username, LocalDate startDate, LocalDate endDate) {

        Booking booking = new Booking();
        booking.setCar(carService.getCarByLicensePlate(licensePlate));
        booking.setUser(userService.getUserByUsername(username));
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setIsApproved(false);

        bookingRepository.save(booking);
    }

    @Override
    public void updateBooking(BookingRequest bookingRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Booking booking = getBooking(
                carService.getCarByLicensePlate(bookingRequest.getLicensePlate()),
                userService.getUserByUsername(bookingRequest.getUsername()),
                LocalDate.parse(bookingRequest.getStartDate(), formatter),
                LocalDate.parse(bookingRequest.getEndDate(), formatter));
        booking.setIsApproved(bookingRequest.isApproved());

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
