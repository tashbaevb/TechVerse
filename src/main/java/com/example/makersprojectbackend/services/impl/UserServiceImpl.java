package com.example.makersprojectbackend.services.impl;

import com.example.makersprojectbackend.entities.SchoolInfo;
import com.example.makersprojectbackend.entities.User;
import com.example.makersprojectbackend.repositories.UserRepository;
import com.example.makersprojectbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User newUser) {
        User oldUser = getById(newUser.getId());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setNameSurname(newUser.getNameSurname());
        SchoolInfo updatedInfo = newUser.getSchoolInfo();
        SchoolInfo existingInfo = oldUser.getSchoolInfo();
        existingInfo.setSchoolNumber(updatedInfo.getSchoolNumber());
        existingInfo.setSchoolName(updatedInfo.getSchoolName());
        existingInfo.setGrade(updatedInfo.getGrade());
        existingInfo.setLocation(updatedInfo.getLocation());
        return userRepository.save(oldUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
