package com.carrentalspringboot.controller;

import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.service.CarService;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/cars")
@CrossOrigin("http://localhost:4200")

public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

// TODO create an exception class
    @GetMapping(value = "/cars", produces = "application/json")
    private ResponseEntity<List<Car>> getCars() {

        List<Car> cars = carService.getCars();
        return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);

    }

    @DeleteMapping(value = "delete/car/{carId}", produces = "application/json")
    @SneakyThrows
    public ResponseEntity<?> deleteCar(@PathVariable("carId") int carId) {

        carService.deleteCar(carId);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/saveCar", produces = "application/json")
    public ResponseEntity<?> saveCar(@RequestBody Car car) {
        carService.saveCar(car);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/getAvailableCars", produces = "application/json")
    public ResponseEntity<List<Car>> getAvailableCars(@RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate) {
        List<Car> availableCars = carService.getAvailableCars(startDate, endDate);
        return new ResponseEntity<List<Car>>(availableCars, HttpStatus.OK);
    }

    @PutMapping(value = "/editCar", produces = "application/json")
    public ResponseEntity<?> editCar(@RequestBody Car car) {
        carService.updateCar(car);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }
}

