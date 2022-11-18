package com.carrentalspringboot.dto;

import lombok.Data;

// Come voglio i dati
@Data
public class CarResponse {

    private String licensePlate;
    private String manufacturer;
    private String model;
    private int year;
    private String type;
    private int seats;

}
