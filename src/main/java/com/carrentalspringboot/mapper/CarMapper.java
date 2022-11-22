package com.carrentalspringboot.mapper;

import com.carrentalspringboot.dto.CarRequest;
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
        return mapper.map(car, CarResponse.class);
    }

    public List<CarResponse> fromEntityToResponse(List<Car> carList) {
        List<CarResponse> responseList = new ArrayList<>();
        for (Car car : carList) {
            responseList.add(fromEntityToResponse(car));
        }
        return responseList;
    }

    public Car fromResponseToEntity(CarRequest carRequest) {

        return mapper.map(carRequest, Car.class);
    }

}