package com.carrentalspringboot.dto;

import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.model.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingResponse {

    private int car;
    private int user;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isApproved;
}
