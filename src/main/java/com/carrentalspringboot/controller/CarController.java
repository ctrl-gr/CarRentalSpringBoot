package com.carrentalspringboot.controller;

import com.carrentalspringboot.dto.CarRequest;
import com.carrentalspringboot.dto.CarResponse;
import com.carrentalspringboot.dto.UserRequest;
import com.carrentalspringboot.mapper.CarMapper;
import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.model.User;
import com.carrentalspringboot.service.CarService;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@RestController
@Builder
@RequestMapping("/cars")
@CrossOrigin("http://localhost:4200")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping(value = "/all", produces = "application/json")
    private ResponseEntity<List<CarResponse>> getCars() {
        List<CarResponse> carResponse = carMapper.fromEntityToResponse(carService.getCars());
        return new ResponseEntity<>(carResponse, HttpStatus.OK);

    }

    @DeleteMapping(value = "delete/{licensePlate}", produces = "application/json")
    public ResponseEntity<?> deleteCar(@PathVariable("licensePlate") String licensePlate) {
        Car car = carService.getCarByLicensePlate(licensePlate);
        carService.deleteCar(car.getId());
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<?> saveCar(CarRequest carRequest) {
        Car car = carMapper.fromResponseToEntity(carRequest);
        carService.saveCar(car);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

     @GetMapping(value = "/get-available-cars", produces = "application/json")
    public ResponseEntity<List<CarResponse>> getAvailableCars(@DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam("startDate") LocalDate startDate, @DateTimeFormat(pattern="yyyy-MM-dd") @RequestParam("endDate") LocalDate endDate) {
        List<CarResponse> availableCars = carMapper.fromEntityToResponse(carService.getAvailableCars(startDate, endDate));
        return new ResponseEntity<>(availableCars, HttpStatus.OK);
    }


    @PutMapping(value = "/edit/{licensePlate}", produces = "application/json")
    public ResponseEntity<?> editCar(@RequestBody CarRequest carRequest, @PathVariable("licensePlate") String licensePlate) {
        int carId = carService.getCarByLicensePlate(licensePlate).getId();
        Car car = carMapper.fromResponseToEntity(carRequest);
        car.setId(carId);
        carService.updateCar(car);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value="/get-car-by-license-plate/{licensePlate}", produces = "application/json")
    public ResponseEntity<Car> getCarByLicensePlate(@PathVariable String licensePlate){
        Car car = carService.getCarByLicensePlate(licensePlate);
        return new ResponseEntity<Car>(car, new HttpHeaders(), HttpStatus.OK);
    }
}

