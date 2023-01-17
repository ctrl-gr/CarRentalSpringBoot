package com.carrentalspringboot.service;

import com.carrentalspringboot.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void storeImageInDb(String username, String path);

    String getImageUrlFromDb(String username);

    void deleteUser(int id);

    List<User> getUsers();

    User getUserByUsername(String username);
}
