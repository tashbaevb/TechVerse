package com.example.makersprojectbackend.controllers;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.entities.User;
import com.example.makersprojectbackend.mappers.UserMapper;
import com.example.makersprojectbackend.repositories.UserRepository;
import com.example.makersprojectbackend.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    @PostMapping("/create")
    public UserDto createUser(@RequestBody User userDetails) {
        return userMapper.convertToDto(userService.create(userDetails));
    }
    @GetMapping("/personal-info")
    public @ResponseBody UserDto showPersonalInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Optional<User> user = userRepository.findByEmail(userEmail);
        return userMapper.convertToDtoOpt(user);
    }
    @GetMapping("/get/{userId}")
    public UserDto getUserById(@PathVariable Long userId) {
        return userMapper.convertToDto(userService.getById(userId));
    }
    @PutMapping("/info/update")
    public UserDto update(@RequestBody User user) {
        return userMapper.convertToDto(userService.update(user));
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
