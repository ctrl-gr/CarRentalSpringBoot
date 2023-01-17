package com.carrentalspringboot.service;


import com.carrentalspringboot.model.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarService {


    boolean moveCar(CarRequest carRequest);

    void saveCar(CarRequest carRequest);

    void deleteCar(int id);

    List<Car> getAvailableCars(LocalDate startDate, LocalDate endDate);

    List<Car> getCars();

    Car getCarByLicensePlate(String licensePlate);

    Car getCarById(int id);

    void updateCar(CarRequest carRequest);
}
