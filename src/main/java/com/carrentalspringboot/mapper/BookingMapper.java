package com.carrentalspringboot.mapper;

import com.carrentalspringboot.dto.BookingRequest;
import com.carrentalspringboot.dto.BookingResponse;
import com.carrentalspringboot.model.Booking;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class BookingMapper {

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
        booking.setStartDate(bookingRequest.getStartDate());
        booking.setEndDate(bookingRequest.getEndDate());
        booking.setIsApproved(bookingRequest.getIsApproved());

        return booking;
    }
}
