package com.example.makersprojectbackend.services;

import com.example.makersprojectbackend.entities.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User getById(Long id);

    User update(User newUser);

    List<User> getAll();

    void delete(Long id);

}
