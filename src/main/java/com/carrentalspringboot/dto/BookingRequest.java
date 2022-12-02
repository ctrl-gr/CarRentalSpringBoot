package com.carrentalspringboot.dto;

import lombok.Data;


@Data
public class BookingRequest {

    private String username;
    private String licensePlate;
    private String startDate;
    private String endDate;
    private boolean isApproved;

}
