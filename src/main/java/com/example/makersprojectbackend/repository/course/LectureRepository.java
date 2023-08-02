package com.example.makersprojectbackend.repository.course;

import com.example.makersprojectbackend.entity.course.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
