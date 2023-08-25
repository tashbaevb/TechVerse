package com.example.makersprojectbackend.service;


import com.example.makersprojectbackend.entity.course.Course;

import java.util.List;

public interface FavoriteService {
    void addToFavorites(Long userId, Long courseId);
    void removeFromFavorites(Long userId, Long courseId);
    List<Course> getUserFavorites(Long userId);
}
