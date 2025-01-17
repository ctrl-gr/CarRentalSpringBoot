package com.carrentalspringboot.controller;


import com.carrentalspringboot.dto.JwtRequest;
import com.carrentalspringboot.dto.JwtResponse;
import com.carrentalspringboot.dto.UserRequest;
import com.carrentalspringboot.mapper.UserMapper;
import com.carrentalspringboot.model.User;
import com.carrentalspringboot.security.JwtTokenUtil;
import com.carrentalspringboot.security.JwtUserDetailsService;
import com.carrentalspringboot.service.UserService;
import lombok.Builder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
@Builder
public class AuthenticationController {

    private final UserMapper userMapper;
    private final UserService userService;

    private final JwtUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;


    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
        User user = userMapper.fromResponseToEntity(userRequest);
        userService.saveUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }
}