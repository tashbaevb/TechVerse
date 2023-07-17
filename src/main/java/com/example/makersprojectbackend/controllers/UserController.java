package com.example.makersprojectbackend.controllers;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.entities.User;
import com.example.makersprojectbackend.mappers.UserMapper;
import com.example.makersprojectbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping("/create")
    public UserDto createUser(@RequestBody User userDetails) {
        return userMapper.convertToDto(userService.create(userDetails));
    }

    @GetMapping("/get/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userMapper.convertToDto(userService.getById(userId));
    }

    @GetMapping("/get/all")
    public List<UserDto> getAllUsers() {
        return userMapper.convertToDtoList(userService.getAll());
    }

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

}
