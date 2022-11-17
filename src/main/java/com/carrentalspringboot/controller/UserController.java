package com.carrentalspringboot.controller;

import com.carrentalspringboot.dto.UserResponse;
import com.carrentalspringboot.mapper.UserMapper;
import com.carrentalspringboot.service.UserService;
import lombok.Builder;
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
    private final UserMapper userMapper;


    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> userResponse = userMapper.fromEntityToResponse(userService.getUsers());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{userId}", produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") int userId) {

        userService.deleteUser(userId);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit", produces = "application/json")
    public ResponseEntity<?> editUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }
}
