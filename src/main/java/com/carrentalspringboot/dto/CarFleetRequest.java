package com.carrentalspringboot.dto;


import lombok.Data;

@Data
public class CarFleetRequest {

    private int id;
    private String headquartersName;
    private String streetAddress;
    private int streetNumber;
    private int zipCode;
    private String city;
    private String province;

}
