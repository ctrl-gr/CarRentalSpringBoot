package com.carrentalspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;

    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private List<Booking> bookings;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private int year;

    @Column(name = "type")
    private String type;

    @Column(name = "seats")
    private int seats;

    public Car() {

    }


}