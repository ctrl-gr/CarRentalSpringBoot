package com.carrentalspringboot.dto;
import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.model.User;
import lombok.Data;




@Data
public class BookingRequest {

    private User user;
    private Car car;
    private String licensePlate;
    private String startDate;
    private String endDate;
    private boolean isApproved;

}
