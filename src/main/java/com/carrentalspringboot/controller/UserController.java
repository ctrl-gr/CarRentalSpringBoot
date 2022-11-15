package com.carrentalspringboot.controller;

import com.carrentalspringboot.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.carrentalspringboot.model.User;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    private List<User> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("/user/{userId}")
    private void deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/saveUser")
    private void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/editUser")
    private User update(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }
}
