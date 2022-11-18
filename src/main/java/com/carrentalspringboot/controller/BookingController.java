package com.carrentalspringboot.controller;

import com.carrentalspringboot.dto.BookingRequest;
import com.carrentalspringboot.dto.BookingResponse;
import com.carrentalspringboot.mapper.BookingMapper;
import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.service.BookingService;
import com.carrentalspringboot.service.UserService;
import lombok.Builder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bookings")
@CrossOrigin("http://localhost:4200")
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;
    private final BookingMapper bookingMapper;



    @GetMapping(value = "/bookings", produces = "application/json")
    private ResponseEntity<List<Booking>> getBookings() {

        List<BookingResponse> bookingsResponse = bookingMapper.fromEntityToResponse(bookingService.getBookings());
        return new ResponseEntity<>(bookingsResponse, HttpStatus.OK);

    }

    @DeleteMapping(value = "delete/booking/{bookingId}", produces = "application/json")
    @SneakyThrows
    public ResponseEntity<?> deleteBooking(@PathVariable("bookingId") int bookingId) {

        bookingService.deleteBooking(bookingId);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/saveBooking", produces = "application/json")
    public ResponseEntity<?> saveBooking(@RequestBody Booking booking) {
        bookingService.saveBooking(booking);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/approve/{bookingId}", produces = "application/json")
    public ResponseEntity<?> approveBooking(@PathVariable("bookingId") int bookingId, @RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingMapper.fromResponseToEntity(bookingRequest);
        booking.setId(bookingId);
        bookingService.updateBooking(booking);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping(value="/my-bookings", produces = "application/json")
    public ResponseEntity<List<Booking>> myBookings(@RequestParam("userId") int userId) {
        List<Booking> myBookings = bookingService.getBookingsByUser(userService.getUserById(userId));
        return new ResponseEntity<>(myBookings, HttpStatus.OK);
    }
}

