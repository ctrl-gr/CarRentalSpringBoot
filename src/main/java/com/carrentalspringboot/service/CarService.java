package com.carrentalspringboot.service;


import com.carrentalspringboot.model.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarService {


    boolean moveCar(int carId);

    void saveCar(Car car);

    void deleteCar(int id);


    List<Car> getAvailableCars(LocalDate startDate, LocalDate endDate);

    List<Car> getCars();

    void updateCar(Car car);

    Car getCarByLicensePlate(String licensePlate);

    Car getCarById(int id);
}
