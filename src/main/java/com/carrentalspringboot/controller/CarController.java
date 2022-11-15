package com.carrentalspringboot.controller;

import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/cars")
    private List<Car> getCars() {
        return carService.getCars();
    }

    @DeleteMapping("/car/{carId}")
    private void deleteUser(@PathVariable("carId") int carId) {
        carService.deleteCar(carId);
    }

    @PostMapping("/saveCar")
    private void saveUser(@RequestBody Car car) {
        carService.saveCar(car);
    }

    @PostMapping("/getAvailableCars")
    private List<Car> getAvailableCars(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        return carService.getAvailableCars(startDate, endDate);
    }

    @PutMapping("/editCar")
    private Car update(@RequestBody Car car) {
        carService.updateCar(car);
        return car;
    }
}

