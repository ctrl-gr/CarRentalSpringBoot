package com.carrentalspringboot.repository;

import com.carrentalspringboot.model.CarFleet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CarFleetRepository extends JpaRepository<CarFleet, Integer>, JpaSpecificationExecutor<CarFleet> {

    CarFleet getCarFleetById(int id);

    CarFleet getCarFleetByHeadquartersName(String headquartersName);
}
