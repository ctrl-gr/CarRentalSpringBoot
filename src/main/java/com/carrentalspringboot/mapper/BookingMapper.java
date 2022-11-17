package com.carrentalspringboot.mapper;

import com.carrentalspringboot.dto.BookingResponse;
import com.carrentalspringboot.model.Booking;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class BookingMapper {

    private final ModelMapper mapper;
    public BookingResponse fromEntityToResponse(Booking booking) {
        BookingResponse bookingResponse = new BookingResponse();
        bookingResponse.setCar(String.format("%s %s", booking.getCar().getManufacturer(), booking.getCar().getModel()));
        bookingResponse.setUser(booking.getUser().getUsername());
        bookingResponse.setStartDate(booking.getStartDate());
        bookingResponse.setEndDate(booking.getEndDate());
        return bookingResponse;
    }

    public List<BookingResponse> fromEntityToResponse(List<Booking> bookingList) {
        List<BookingResponse> responseList = new ArrayList<>();
        for (Booking booking : bookingList) {
            responseList.add(fromEntityToResponse(booking));
        }
        return responseList;
    }
}
