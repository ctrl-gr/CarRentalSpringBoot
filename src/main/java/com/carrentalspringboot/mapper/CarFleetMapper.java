package com.carrentalspringboot.mapper;

import com.carrentalspringboot.dto.CarFleetRequest;
import com.carrentalspringboot.dto.CarFleetResponse;
import com.carrentalspringboot.model.CarFleet;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor

public class CarFleetMapper {

    private final ModelMapper mapper;

    public CarFleetResponse fromEntityToResponse(CarFleet carFleet) {
        CarFleetResponse carFleetResponse = new CarFleetResponse();
        carFleetResponse.setId(carFleet.getId());
        carFleetResponse.setHeadquartersName(carFleet.getHeadquartersName());
        carFleetResponse.setStreetAddress(carFleet.getStreetAddress());
        carFleetResponse.setStreetNumber(carFleet.getStreetNumber());
        carFleetResponse.setZipCode(carFleet.getZipCode());
        carFleetResponse.setCity(carFleet.getCity());
        carFleetResponse.setProvince(carFleet.getProvince());

        return carFleetResponse;
    }

    public List<CarFleetResponse> fromEntityToResponse(List<CarFleet> carFleetList) {
        List<CarFleetResponse> responseList = new ArrayList<>();
        for (CarFleet carFleet : carFleetList) {
            responseList.add(fromEntityToResponse(carFleet));
        }
        return responseList;
    }

    public CarFleet fromResponseToEntity(CarFleetRequest carFleetRequest) {
        CarFleet carFleet = new CarFleet();
        carFleet.setHeadquartersName(carFleetRequest.getHeadquartersName());
        carFleet.setStreetAddress(carFleetRequest.getStreetAddress());
        carFleet.setStreetNumber(carFleetRequest.getStreetNumber());
        carFleet.setZipCode(carFleetRequest.getZipCode());
        carFleet.setCity(carFleetRequest.getCity());
        carFleet.setProvince(carFleetRequest.getProvince());

        return carFleet;
    }

}
