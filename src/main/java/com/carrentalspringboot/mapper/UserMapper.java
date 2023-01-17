package com.carrentalspringboot.mapper;


import com.carrentalspringboot.dto.UserRequest;
import com.carrentalspringboot.dto.UserResponse;
import com.carrentalspringboot.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse fromEntityToResponse(User user) {
        return mapper.map(user, UserResponse.class);
    }

    public List<UserResponse> fromEntityToResponse(List<User> userList) {
        List<UserResponse> responseList = new ArrayList<>();
        for (User user : userList) {
            responseList.add(fromEntityToResponse(user));
        }
        return responseList;
    }

    public User fromResponseToEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setBirthDate((userRequest.getBirthDate()));
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return user;
    }

}
