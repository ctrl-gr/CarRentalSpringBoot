package com.carrentalspringboot.mapper;

import com.carrentalspringboot.dto.BookingRequest;
import com.carrentalspringboot.dto.BookingResponse;
import com.carrentalspringboot.model.Booking;
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

    public BookingResponse fromEntityToResponse(Booking booking) {
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setCar(booking.getCar().getId());
        bookingResponse.setUser(booking.getUser().getId());
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


    public Booking fromResponseToEntity(BookingRequest bookingRequest) {
        Booking booking = new Booking();
        booking.setUser(bookingRequest.getUser());
        booking.setCar(bookingRequest.getCar());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(bookingRequest.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(bookingRequest.getEndDate(), formatter);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setIsApproved(bookingRequest.isApproved());

        return booking;
    }


}
