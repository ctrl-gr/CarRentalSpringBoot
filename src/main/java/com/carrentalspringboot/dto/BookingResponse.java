package com.carrentalspringboot.dto;

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
