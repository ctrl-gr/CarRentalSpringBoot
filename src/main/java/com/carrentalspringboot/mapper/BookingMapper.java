package com.carrentalspringboot.mapper;

import com.carrentalspringboot.dto.BookingRequest;
import com.carrentalspringboot.dto.BookingResponse;
import com.carrentalspringboot.dto.CarResponse;
import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.model.Car;
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
        return mapper.map(bookingRequest, Booking.class);
    }
}
