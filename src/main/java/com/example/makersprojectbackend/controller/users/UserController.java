package com.example.makersprojectbackend.controller.users;

import com.example.makersprojectbackend.dto.UserDto;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.forms.Enroll;
import com.example.makersprojectbackend.entity.forms.Feedback;
import com.example.makersprojectbackend.mappers.UserMapper;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.service.UserService;
//<<<<<<< HEAD:src/main/java/com/example/makersprojectbackend/controller/UserController.java
import com.example.makersprojectbackend.service.impl.UserServiceImpl;

//=======
//>>>>>>> khashem:src/main/java/com/example/makersprojectbackend/controller/users/UserController.java
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserService userService;


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


    @PutMapping("/sub/{courseId}") //записаться на курс
    public void enrollPaidCourse(@PathVariable Long courseId, @RequestBody Enroll enroll) throws Exception {
        userService.enrollPaidCourse(courseId, enroll);
    }


    @PutMapping("/feedback") //связаться
    public ResponseEntity<String> feedback(@RequestBody Feedback feedback) {
        return userService.makeFeedback(feedback);
    }
}
