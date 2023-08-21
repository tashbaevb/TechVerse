package com.example.makersprojectbackend.repository.course;

import com.example.makersprojectbackend.entity.Favorite;
import com.example.makersprojectbackend.entity.User;
import com.example.makersprojectbackend.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Favorite findByUserAndCourse(User user, Course course);
}
