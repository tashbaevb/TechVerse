package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.mappers.UserMapper;
import com.example.makersprojectbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/create")
    public UserDto createUser(@RequestBody User user) {
        return userMapper.convertToDTO(userService.create(user));
    }

    @GetMapping("/get/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.convertToDTO(userService.getById(id));
    }

    @GetMapping("/get/all")
    public List<UserDto> getAllUsers() {
        return userMapper.convertToDTOList(userService.getAll());
    }

    @PutMapping("/update")
    public UserDto updateUser(@RequestBody User userDetails) {
        return userMapper.convertToDTO(userService.update(userDetails));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
