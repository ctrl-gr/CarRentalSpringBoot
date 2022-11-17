package com.carrentalspringboot.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="birth_date")
    private Date birthDate;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="is_admin")
    private boolean isAdmin;


}
