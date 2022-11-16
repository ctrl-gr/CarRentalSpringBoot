package com.carrentalspringboot.repository;

import com.carrentalspringboot.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "SELECT C.CAR_ID, B.BOOKING_ID FROM BOOKING AS B INNER JOIN CAR AS C ON B.CAR_ID = C.CAR_ID WHERE C.CAR_ID NOT IN (SELECT CAR_ID FROM B WHERE C.CAR_ID = B.CAR_ID)", nativeQuery = true)
    availableCars<Car> findAllById();

}

