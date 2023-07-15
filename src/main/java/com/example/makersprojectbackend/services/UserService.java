package com.example.makersprojectbackend.services;

import com.example.makersprojectbackend.entities.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User getById(Long id);

    List<User> getAll();

    User update(User userDetails);

    void delete(Long id);

}
