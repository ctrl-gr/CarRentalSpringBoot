package com.carrentalspringboot.service;

import com.carrentalspringboot.model.CarFleet;
import com.carrentalspringboot.repository.CarFleetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor

public class CarFleetServiceImpl implements CarFleetService {

    private final CarFleetRepository carFleetRepository;

    @Override
    public void saveCarFleet(CarFleet carFleet) {
        carFleetRepository.save(carFleet);
    }

    @Override
    public void deleteCarFleet(int id) {
        carFleetRepository.deleteById(id);
    }

    @Override
    public List<CarFleet> getCarFleets() {
        List<CarFleet> carFleets = new ArrayList<>();
        carFleetRepository.findAll().forEach(carFleetToAdd -> carFleets.add(carFleetToAdd));
        return carFleets;
    }


    @Override
    public CarFleet getCarFleetById(int id) {
        return carFleetRepository.getCarFleetById(id);
    }

    @Override
    public CarFleet getCarFleetByHeadquartersName(String headquartersName) {
        return carFleetRepository.getCarFleetByHeadquartersName(headquartersName);
    }
}
