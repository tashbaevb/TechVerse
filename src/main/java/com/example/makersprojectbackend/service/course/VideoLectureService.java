package com.example.makersprojectbackend.service.course;

import com.example.makersprojectbackend.entity.course.VideoLecture;

import java.util.List;

public interface VideoLectureService {
    VideoLecture create(VideoLecture videoLecture);

    VideoLecture getById(Long id);

    List<VideoLecture> getAll();

    VideoLecture update(VideoLecture videoLecture);

    void delete(Long id);
}
