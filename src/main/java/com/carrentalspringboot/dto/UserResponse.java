package com.carrentalspringboot.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class UserResponse {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String username;
    private String password;
    private boolean isAdmin;
    private String image;
}
