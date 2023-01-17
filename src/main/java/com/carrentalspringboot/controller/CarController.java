package com.carrentalspringboot.controller;

import com.carrentalspringboot.dto.CarRequest;
import com.carrentalspringboot.dto.CarResponse;
import com.carrentalspringboot.mapper.CarMapper;
import com.carrentalspringboot.service.CarService;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @DeleteMapping(value = "delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteCar(@PathVariable("id") int id) {
        carService.deleteCar(id);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<?> saveCar(@RequestBody CarRequest carRequest) {
        carService.saveCar(carRequest);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/get-available-cars", produces = "application/json")
    public ResponseEntity<List<CarResponse>> getAvailableCars(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("startDate") LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam("endDate") LocalDate endDate) {
        List<CarResponse> availableCars = carMapper.fromEntityToResponse(carService.getAvailableCars(startDate, endDate));
        return new ResponseEntity<>(availableCars, HttpStatus.OK);
    }


    @PutMapping(value = "/edit", produces = "application/json")
    public ResponseEntity<?> editCar(@RequestBody CarRequest carRequest) {
        try {
            carService.updateCar(carRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/get-car-by-id/{id}", produces = "application/json")
    public ResponseEntity<CarResponse> getCarById(@PathVariable int id) {
        CarResponse carResponse = carMapper.fromEntityToResponse(carService.getCarById(id));
        return new ResponseEntity<>(carResponse, new HttpHeaders(), HttpStatus.OK);
    }
}

