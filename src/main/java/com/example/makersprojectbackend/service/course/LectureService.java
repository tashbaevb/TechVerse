package com.example.makersprojectbackend.service.course;

import com.example.makersprojectbackend.entity.course.Lecture;

import java.util.List;

public interface LectureService {
    Lecture create(Lecture lecture);

    Lecture getById(Long id);

    List<Lecture> getAll();

    Lecture update(Lecture lecture);

    void delete(Long id);
}
