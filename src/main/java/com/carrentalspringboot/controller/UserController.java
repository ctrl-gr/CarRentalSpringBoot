package com.carrentalspringboot.controller;

import com.carrentalspringboot.dto.UserRequest;
import com.carrentalspringboot.dto.UserResponse;
import com.carrentalspringboot.mapper.UserMapper;
import com.carrentalspringboot.model.User;
import com.carrentalspringboot.service.UserService;
import lombok.Builder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4200")
@Builder
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> userResponse = userMapper.fromEntityToResponse(userService.getUsers());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}", produces = "application/json")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/save", produces = "application/json")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
        User user = userMapper.fromResponseToEntity(userRequest);
        userService.saveUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit/{username}", produces = "application/json")
    public ResponseEntity<?> editUser(@RequestBody UserRequest userRequest, @PathVariable String username) {
        int userId = userService.getUserByUsername(username).getId();
        User user = userMapper.fromResponseToEntity(userRequest);
        user.setId(userId);
        userService.saveUser(user);
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/get-user-by-username/{username}", produces = "application/json")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/upload-picture/{username}")
    public ResponseEntity<?> handleFileUpload(@RequestParam("image") MultipartFile file, @PathVariable String username) {
        String fileName = file.getOriginalFilename();
        File normalFile = new File("C:\\Users\\Si2001\\Desktop\\upload\\" + fileName);
        try {
            file.transferTo(normalFile);
           userService.storeImageInDb(username, normalFile.getAbsolutePath());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/get-image/{username}")
    public ResponseEntity<byte[]> getImage(@PathVariable String username) throws IOException{
        File img = new File(userService.getImageUrlFromDb(username));
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }


}
