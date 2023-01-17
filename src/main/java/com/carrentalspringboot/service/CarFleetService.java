package com.carrentalspringboot.service;

import com.carrentalspringboot.model.CarFleet;

import java.util.List;

public interface CarFleetService {

    void saveCarFleet(CarFleet carFleet);

    void deleteCarFleet(int id);

    List<CarFleet> getCarFleets();

    CarFleet getCarFleetById(int id);

    CarFleet getCarFleetByHeadquartersName(String headquartersName);
}
