package com.carrentalspringboot.service;


import com.carrentalspringboot.model.Car;

import java.util.Date;
import java.util.List;

public interface CarService {

    void saveCar(Car car);

    void deleteCar(int id);

    Car getCarById(int id);

    List<Car> getAvailableCars(Date startDate, Date endDate);

    List<Car> getCars();

    void updateCar(Car car);
}
