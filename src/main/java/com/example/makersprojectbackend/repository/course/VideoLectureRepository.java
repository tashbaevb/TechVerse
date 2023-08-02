package com.example.makersprojectbackend.repository.course;

import com.example.makersprojectbackend.entity.course.VideoLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoLectureRepository extends JpaRepository<VideoLecture, Long> {
}
