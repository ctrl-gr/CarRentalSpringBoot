package com.carrentalspringboot.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingResponse {

    private String manufacturerModel;
    private String licensePlate;
    private String username;
    private LocalDate startDate;
    private LocalDate endDate;
    private String headquartersName;
    private Boolean isApproved;
}
