package com.carrentalspringboot.controller;

import com.carrentalspringboot.dto.CarRequest;
import com.carrentalspringboot.dto.CarResponse;
import com.carrentalspringboot.mapper.CarMapper;
import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.service.CarService;
import lombok.Builder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//TODO implement throw exceptions
@RestController
@Builder
@RequestMapping("/cars")
@CrossOrigin("*") // localhost 4200
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;

    @GetMapping(value = "/all", produces = "application/json")
    private ResponseEntity<List<CarResponse>> getCars() {
        List<CarResponse> carResponse = carMapper.fromEntityToResponse(carService.getCars());
        return new ResponseEntity<>(carResponse, HttpStatus.OK);

    }

    @DeleteMapping(value = "delete/{carId}", produces = "application/json")
    public ResponseEntity<?> deleteCar(@PathVariable("carId") int carId) {
        carService.deleteCar(carId);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<Car> saveCar(@RequestBody CarRequest carRequest) {
        Car car = carMapper.fromResponseToEntity(carRequest);
        carService.saveCar(car);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-available-cars", produces = "application/json")
    public ResponseEntity<List<Car>> getAvailableCars(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        List<Car> availableCars = carService.getAvailableCars(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return new ResponseEntity<>(availableCars, HttpStatus.OK);
    }

    @PutMapping(value = "/edit/{carId}", produces = "application/json")
    public ResponseEntity<?> editCar(@PathVariable("carId") int carId, @RequestBody CarRequest carRequest) {
        Car car = carMapper.fromResponseToEntity(carRequest);
        car.setId(carId);
        carService.updateCar(car);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}

