package com.carrentalspringboot.controller;

import com.carrentalspringboot.dto.BookingRequest;
import com.carrentalspringboot.dto.BookingResponse;
import com.carrentalspringboot.mapper.BookingMapper;
import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.service.BookingService;
import com.carrentalspringboot.service.CarService;
import com.carrentalspringboot.service.UserService;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin("http://localhost:4200")
@Builder
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;

    private final CarService carService;
    private final BookingMapper bookingMapper;


    @GetMapping(value = "/all", produces = "application/json")
    private ResponseEntity<List<BookingResponse>> getBookings() {

        List<BookingResponse> bookingsResponse = bookingMapper.fromEntityToResponse(bookingService.getBookings());
        return new ResponseEntity<>(bookingsResponse, HttpStatus.OK);

    }

    @PostMapping(value = "/delete", produces = "application/json")
    public ResponseEntity<?> deleteBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingMapper.fromResponseToEntityToApproveOrDelete(bookingRequest);
        bookingService.deleteBooking(booking);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<?> saveBooking(@RequestParam("licensePlate") String licensePlate, @RequestParam("username") String username, @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("startDate") LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        Booking booking = bookingMapper.fromResponseToEntityToSave(licensePlate, username, startDate, endDate);
        bookingService.saveBooking(booking);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/approve", produces = "application/json")
    public ResponseEntity<?> approveBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = bookingMapper.fromResponseToEntityToApproveOrDelete(bookingRequest);
        bookingService.updateBooking(booking);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/my-bookings/{username}", produces = "application/json")
    public ResponseEntity<List<BookingResponse>> myBookings(@PathVariable("username") String username) {
        List<BookingResponse> myBookings = bookingMapper.fromEntityToResponseMyBookings(username);
        return new ResponseEntity<>(myBookings, HttpStatus.OK);
    }
}

