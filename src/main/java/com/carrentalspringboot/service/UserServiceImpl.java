package com.carrentalspringboot.service;

import com.carrentalspringboot.model.User;
import com.carrentalspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(userToAdd -> users.add(userToAdd));
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void storeImageInDb(String username, String path) {
        User user = getUserByUsername(username);
        user.setImage(path);
        userRepository.save(user);
    }

    @Override
    public String getImageUrlFromDb(String username) {
        User user = getUserByUsername(username);
        String path = user.getImage();
        return path;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
