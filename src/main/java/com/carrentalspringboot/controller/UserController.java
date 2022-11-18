package com.carrentalspringboot.controller;

import com.carrentalspringboot.dto.UserRequest;
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
@RequestMapping("/users")
@CrossOrigin("*") // localhost 4200
@Builder
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
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
        User user = userMapper.fromResponseToEntity(userRequest);
        userService.saveUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit/{userId}", produces = "application/json")
    public ResponseEntity<?> editUser(@PathVariable("userId") int userId, @RequestBody UserRequest userRequest) {
        User user = userMapper.fromResponseToEntity(userRequest);
        user.setId(userId);
        userService.updateUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }
}
