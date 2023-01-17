package com.carrentalspringboot.controller;

import com.carrentalspringboot.dto.CarFleetRequest;
import com.carrentalspringboot.dto.CarFleetResponse;
import com.carrentalspringboot.mapper.CarFleetMapper;
import com.carrentalspringboot.model.CarFleet;
import com.carrentalspringboot.service.CarFleetService;
import lombok.Builder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carfleets")
@CrossOrigin("http://localhost:4200")
@Builder

public class CarFleetController {

    private final CarFleetService carFleetService;
    private final CarFleetMapper carFleetMapper;

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<CarFleetResponse>> getCarFleets() {
        List<CarFleetResponse> carFleetResponse = carFleetMapper.fromEntityToResponse(carFleetService.getCarFleets());
        return new ResponseEntity<>(carFleetResponse, HttpStatus.OK);
    }


    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteCarFleet(@PathVariable("id") int id) {
        carFleetService.deleteCarFleet(id);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<?> saveCarFleet(@RequestBody CarFleetRequest carFleetRequest) {
        CarFleet carFleet = carFleetMapper.fromResponseToEntity(carFleetRequest);
        carFleetService.saveCarFleet(carFleet);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }


    @PutMapping(value = "/edit", produces = "application/json")
    public ResponseEntity<?> editCarFleet(@RequestBody CarFleetRequest carFleetRequest) {
        int carFleetId = carFleetRequest.getId();
        CarFleet carFleet = carFleetMapper.fromResponseToEntity(carFleetRequest);
        carFleet.setId(carFleetId);
        carFleetService.saveCarFleet(carFleet);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping(value = "/get-carfleet-by-id/{id}", produces = "application/json")
    public ResponseEntity<CarFleetResponse> getCarFleetByHeadquartersName(@PathVariable int id) {
        CarFleetResponse carFleetResponse = carFleetMapper.fromEntityToResponse(carFleetService.getCarFleetById(id));
        return new ResponseEntity<>(carFleetResponse, new HttpHeaders(), HttpStatus.OK);
    }
}
