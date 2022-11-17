package com.carrentalspringboot.mapper;


import com.carrentalspringboot.dto.UserResponse;
import com.carrentalspringboot.model.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class UserMapper {

    private final ModelMapper mapper;

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
}
