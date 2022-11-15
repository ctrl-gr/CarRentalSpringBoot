package com.carrentalspringboot.service;

import com.carrentalspringboot.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void deleteUser(int id);

    List<User> getUsers();

    User getUserById(int id);

    void updateUser(User user);
}
