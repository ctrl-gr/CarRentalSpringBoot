package com.carrentalspringboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingResponse {

    private String car;
    private String user;
    private LocalDate startDate;
    private LocalDate endDate;
}
