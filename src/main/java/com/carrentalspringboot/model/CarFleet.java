package com.carrentalspringboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "car_fleet")

public class CarFleet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_fleet_id")
    private int id;

    @OneToMany(mappedBy = "carFleet")
    private List<Car> carList;

    @Column(name = "headquarters_name")
    private String headquartersName;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "street_number")
    private int streetNumber;

    @Column(name = "zip_code")
    private int zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    public CarFleet() {

    }
}
