package com.carrentalspringboot.service;

import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.repository.CarRepository;
import com.carrentalspringboot.specifications.CanMoveCarSpecification;
import com.carrentalspringboot.specifications.CarSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor


public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    private final CarFleetService carFleetService;

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


    /*
    - Ricontrollare la query
     */

    @Override
    public boolean moveCar(CarRequest carRequest) {
        List<Car> carAvailable = new ArrayList<>();
        carRepository.findAll(
                CanMoveCarSpecification
                        .builder()
                        .carId(carRequest.getId())
                        .build()).forEach(carToAdd -> carAvailable.add(carToAdd));
        Car car = getCarById(carRequest.getId());
            if (carAvailable.contains(car)) {
                return true;
            } else {
                return false;
            }
    }

    @Override
    public void saveCar(CarRequest carRequest) {

        Car car = new Car();
        car.setLicensePlate(carRequest.getLicensePlate());
        car.setManufacturer(carRequest.getManufacturer());
        car.setModel(carRequest.getModel());
        car.setType(carRequest.getType());
        car.setYear(carRequest.getYear());
        car.setSeats(carRequest.getSeats());
        car.setCarFleet(carFleetService.getCarFleetByHeadquartersName(carRequest.getHeadquartersName()));

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

    @Override
    public Car getCarByLicensePlate(String licensePlate) {
        return carRepository.getCarByLicensePlate(licensePlate);
    }

    @Override
    public Car getCarById(int carId) {
        return carRepository.getCarById(carId);
    }

    @Override
    public void updateCar(CarRequest carRequest) {
        Car car = getCarById(carRequest.getId());

        if (!carRequest.getHeadquartersName().equals(car.getCarFleet().getHeadquartersName())) {
            if (moveCar(carRequest)) {
                car.setId(carRequest.getId());
                car.setLicensePlate(carRequest.getLicensePlate());
                car.setManufacturer(carRequest.getManufacturer());
                car.setModel(carRequest.getModel());
                car.setType(carRequest.getType());
                car.setYear(carRequest.getYear());
                car.setSeats(carRequest.getSeats());
                car.setCarFleet(carFleetService.getCarFleetByHeadquartersName(carRequest.getHeadquartersName()));

                carRepository.save(car);
            } else {
                throw new RuntimeException("Error during car editing. Try again.");
            }
        } else {
            car.setId(carRequest.getId());
            car.setLicensePlate(carRequest.getLicensePlate());
            car.setManufacturer(carRequest.getManufacturer());
            car.setModel(carRequest.getModel());
            car.setType(carRequest.getType());
            car.setYear(carRequest.getYear());
            car.setSeats(carRequest.getSeats());
            car.setCarFleet(carFleetService.getCarFleetByHeadquartersName(carRequest.getHeadquartersName()));

            carRepository.save(car);
        }
    }
}
