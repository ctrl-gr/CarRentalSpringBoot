package com.carrentalspringboot.mapper;

import com.carrentalspringboot.dto.CarRequest;
import com.carrentalspringboot.dto.CarResponse;
import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.service.CarFleetService;
import com.carrentalspringboot.service.CarService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@Data

public class CarMapper {

    private final ModelMapper mapper;

    private final CarService carService;
    private final CarFleetService carFleetService;

    public CarResponse fromEntityToResponse(Car car) {
        CarResponse carResponse = new CarResponse();
        carResponse.setId(car.getId());
        carResponse.setLicensePlate(car.getLicensePlate());
        carResponse.setManufacturer(car.getManufacturer());
        carResponse.setModel(car.getModel());
        carResponse.setYear(car.getYear());
        carResponse.setType(car.getType());
        carResponse.setSeats(car.getSeats());
        carResponse.setHeadquartersName(car.getCarFleet().getHeadquartersName());

        return carResponse;
    }

    public CarResponse fromEntityToResponseWithLicensePlate(String licensePlate) {
        CarResponse carResponse = new CarResponse();
        Car car = carService.getCarByLicensePlate(licensePlate);
        carResponse.setLicensePlate(car.getLicensePlate());
        carResponse.setManufacturer(car.getManufacturer());
        carResponse.setModel(car.getModel());
        carResponse.setYear(car.getYear());
        carResponse.setType(car.getType());
        carResponse.setSeats(car.getSeats());
        carResponse.setHeadquartersName(car.getCarFleet().getHeadquartersName());

        return carResponse;
    }

    public List<CarResponse> fromEntityToResponse(List<Car> carList) {
        List<CarResponse> responseList = new ArrayList<>();
        for (Car car : carList) {
            responseList.add(fromEntityToResponse(car));
        }
        return responseList;
    }

    public Car fromResponseToEntity(CarRequest carRequest) {
        Car car = new Car();
        car.setLicensePlate(carRequest.getLicensePlate());
        car.setManufacturer(carRequest.getManufacturer());
        car.setModel(carRequest.getModel());
        car.setType(carRequest.getType());
        car.setYear(carRequest.getYear());
        car.setSeats(carRequest.getSeats());
        car.setCarFleet(carFleetService.getCarFleetByHeadquartersName(carRequest.getHeadquartersName()));

        return car;
    }

}