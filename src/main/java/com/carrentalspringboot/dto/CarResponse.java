package com.carrentalspringboot.dto;

import lombok.Data;


@Data
public class CarResponse {

    private int id;
    private String licensePlate;
    private String manufacturer;
    private String model;
    private int year;
    private String type;
    private int seats;
    private String headquartersName;

}
