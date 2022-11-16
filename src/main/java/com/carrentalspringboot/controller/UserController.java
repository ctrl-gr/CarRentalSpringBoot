package com.carrentalspringboot.controller;

import com.carrentalspringboot.model.Car;
import com.carrentalspringboot.service.UserService;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.carrentalspringboot.model.User;

import java.util.List;

@RestController
@RequestMapping("api/users")
@CrossOrigin("http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = userService.getUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);

    }
    @DeleteMapping(value = "delete/user/{userId}", produces = "application/json")
    @SneakyThrows
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {

        userService.deleteUser(userId);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/saveUser", produces = "application/json")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/editUser", produces = "application/json")
    public ResponseEntity<?> editUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }
}
