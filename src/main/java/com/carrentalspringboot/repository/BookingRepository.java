package com.carrentalspringboot.repository;

import com.carrentalspringboot.model.Booking;
import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>, JpaSpecificationExecutor<Booking> {

    Booking findByCarAndAndUserAndStartDateAndEndDate(Car car, User user, LocalDate startDate, LocalDate endDate);
}