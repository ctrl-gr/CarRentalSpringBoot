package com.carrentalspringboot.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String username;
    private String password;
    private boolean isAdmin;
}
