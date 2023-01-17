package com.carrentalspringboot.mapper;

import com.carrentalspringboot.dto.CarResponse;
import com.carrentalspringboot.model.Car;
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

    public List<CarResponse> fromEntityToResponse(List<Car> carList) {
        List<CarResponse> responseList = new ArrayList<>();
        for (Car car : carList) {
            responseList.add(fromEntityToResponse(car));
        }
        return responseList;
    }

}

