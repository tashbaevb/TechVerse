package com.example.makersprojectbackend.controller;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.forms.Application;
import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.mappers.UserMapper;
import com.example.makersprojectbackend.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final UserMapper userMapper;

    @PostMapping("/create")
    public UserDto createUser(@RequestBody User user) {
        return userMapper.convertToDTO(userServiceImpl.create(user));
    }

    @GetMapping("/get/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userMapper.convertToDTO(userServiceImpl.getById(id));
    }

    @GetMapping("/get/all")
    public List<UserDto> getAllUsers() {
        return userMapper.convertToDTOList(userServiceImpl.getAll());
    }

    @PutMapping("/update")
    public UserDto updateUser(@RequestBody User userDetails) {
        return userMapper.convertToDTO(userServiceImpl.update(userDetails));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userServiceImpl.delete(id);
    }

    @PutMapping("/sub/{courseId}") //записаться на курс
    public void submit(@PathVariable Long courseId, @RequestBody Application application) throws Exception {
        userServiceImpl.submit(courseId, application);
    }

    @PutMapping("/feedback") //связаться
    public ResponseEntity<String> feedback(@RequestBody Feedback feedback) {
        return userServiceImpl.makeFeedback(feedback);
    }
}
