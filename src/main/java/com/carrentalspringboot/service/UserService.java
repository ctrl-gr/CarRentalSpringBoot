package com.carrentalspringboot.service;

import com.carrentalspringboot.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void deleteUser(int id);

    List<User> getUsers();

    void updateUser(User user);

    User getUserById(int id);

    User getUserByUsername(String username);
}
