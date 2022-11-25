package com.carrentalspringboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.repository.CarRepository;
import com.carrentalspringboot.specifications.CarSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(carToAdd -> cars.add(carToAdd));
        return cars;
    }


    @Override
    public List<Car> getAvailableCars(LocalDate startDate, LocalDate endDate) {
        return carRepository.findAll(
                CarSpecification
                        .builder()
                        .startDate(startDate)
                        .endDate(endDate)
                        .build());
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteCar(int id) {
        carRepository.deleteById(id);
    }

    @Override
    public void updateCar(Car car) {

        carRepository.save(car);
    }
}
