package com.carrentalspringboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @Override
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<Car>();
        carRepository.findAll().forEach(carToAdd -> cars.add(carToAdd));
        return cars;
    }

    @Override
    public Car getCarById(int id) {
        return carRepository.findById(id).get();
    }

    @Override
    public List<Car> getAvailableCars(Date startDate, Date endDate) {
        return null;    }
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
