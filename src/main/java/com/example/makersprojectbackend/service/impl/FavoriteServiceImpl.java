package com.example.makersprojectbackend.service.impl;

import com.example.makersprojectbackend.entity.Favorite;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.course.Course;
import com.example.makersprojectbackend.repository.UserRepository;
import com.example.makersprojectbackend.repository.course.CourseRepository;
import com.example.makersprojectbackend.repository.course.FavoriteRepository;
import com.example.makersprojectbackend.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final FavoriteRepository favoriteRepository;

    @Override
    public void addToFavorites(Long userId, Long courseId) {
        User user = userRepository.findById(userId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        if (course != null) {
            Favorite existingFavorite = favoriteRepository.findByUserAndCourse(user, course);
            if (existingFavorite == null) {
                Favorite newFavorite = new Favorite();
                newFavorite.setUser(user);
                newFavorite.setCourse(course);
                favoriteRepository.save(newFavorite);
            } else {
                throw new RuntimeException("This course is already in your favorites.");
            }
        }
    }



    @Override
    public void removeFromFavorites(Long userId, Long courseId) {
        User user = userRepository.findById(userId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (user != null && course != null) {
            Favorite favorite = favoriteRepository.findByUserAndCourse(user, course);
            if (favorite != null) {
                favoriteRepository.delete(favorite);
            }
        }
    }

    @Override
    public List<Course> getUserFavorites(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<Course> favoriteCourses = new ArrayList<>();

        if (user != null) {
            List<Favorite> favorites = user.getFavorites();
            for (Favorite favorite : favorites) {
                favoriteCourses.add(favorite.getCourse());
            }
        }

        return favoriteCourses;
    }
}
