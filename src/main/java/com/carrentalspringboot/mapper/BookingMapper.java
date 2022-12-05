package com.carrentalspringboot.mapper;

import com.carrentalspringboot.dto.BookingRequest;
import com.carrentalspringboot.dto.BookingResponse;
import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.service.BookingService;
import com.carrentalspringboot.service.CarService;
import com.carrentalspringboot.service.UserService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
@Builder
@RequiredArgsConstructor
public class BookingMapper {

    private final UserService userService;
    private final CarService carService;
    private final BookingService bookingService;

    public BookingResponse fromEntityToResponse(Booking booking) {
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setLicensePlate(booking.getCar().getLicensePlate());
        bookingResponse.setUsername(booking.getUser().getUsername());
        bookingResponse.setStartDate(booking.getStartDate());
        bookingResponse.setEndDate(booking.getEndDate());
        bookingResponse.setIsApproved(booking.getIsApproved());

        return bookingResponse;
    }

    public List<BookingResponse> fromEntityToResponse(List<Booking> bookingList) {
        List<BookingResponse> responseList = new ArrayList<>();
        for (Booking booking : bookingList) {
            responseList.add(fromEntityToResponse(booking));
        }
        return responseList;
    }

    public List<BookingResponse> fromEntityToResponseMyBookings(String username) {
        List<BookingResponse> myBookingsResponse = new ArrayList<>();
        BookingResponse myBookingResponse = new BookingResponse();
        List<Booking> myBookings = bookingService.getBookingsByUser(userService.getUserByUsername(username));
        for (Booking myBooking : myBookings) {

            myBookingResponse.setManufacturerModel(myBooking.getCar().getManufacturer() + " " + myBooking.getCar().getModel());
            myBookingResponse.setStartDate(myBooking.getStartDate());
            myBookingResponse.setEndDate(myBooking.getEndDate());
            myBookingResponse.setIsApproved(myBooking.getIsApproved());
            myBookingsResponse.add(myBookingResponse);
        }

        return myBookingsResponse;
    }


    public Booking fromResponseToEntityToApproveOrDelete(BookingRequest bookingRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Booking booking = bookingService.getBooking(
                carService.getCarByLicensePlate(bookingRequest.getLicensePlate()),
                userService.getUserByUsername(bookingRequest.getUsername()),
                LocalDate.parse(bookingRequest.getStartDate(), formatter),
                LocalDate.parse(bookingRequest.getEndDate(), formatter)
        );
        booking.setIsApproved(!bookingRequest.isApproved());

        return booking;
    }

    public Booking fromResponseToEntityToSave(String licensePlate, String username, LocalDate startDate, LocalDate endDate) {

        Booking booking = new Booking();
        booking.setCar(carService.getCarByLicensePlate(licensePlate));
        booking.setUser(userService.getUserByUsername(username));
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setIsApproved(false);


        return booking;
    }

}
