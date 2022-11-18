package com.carrentalspringboot.dto;

import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.model.User;
import lombok.Data;

import java.time.LocalDate;


@Data
public class BookingRequest {
    private Car car;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isApproved;

}
