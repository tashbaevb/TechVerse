package com.example.makersprojectbackend.repository;

import com.example.makersprojectbackend.entity.VideoLecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoLectureRepository extends JpaRepository<VideoLecture, Long> {
}
